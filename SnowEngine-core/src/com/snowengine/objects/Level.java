/**
 * Copyright (c) 2016 Mark Rienstra
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.snowengine.objects;

import com.snowengine.graphics.Shader;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.colliders.CollisionManager;
import com.snowengine.objects.entities.AnimatedEntity;
import com.snowengine.objects.entities.Entity;
import com.snowengine.objects.entities.EntityBase;
import com.snowengine.objects.lighting.AmbientLight;
import com.snowengine.objects.lighting.Light;
import com.snowengine.objects.tiles.AnimatedTile;
import com.snowengine.objects.tiles.Tile;
import com.snowengine.objects.tiles.TileBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Level extends GameObject
{
    private static Level m_ActiveLevel = null;
    private List<TileBase> m_Tiles;
    private List<Light> m_Lights;
    private List<EntityBase> m_Entities;
    private Comparator<EntityBase> m_EntityComparator;
    private AmbientLight m_AmbientLight;
    private Shader m_Shader;
    private CollisionManager m_CollisionManager;
    
    public Level()
    {
        super ("Level", 9);
        
        if (m_ActiveLevel == null)
        {
            m_ActiveLevel = this;
        }
        
        m_Tiles = new ArrayList<>();
        m_Lights = new ArrayList<>();
        m_Entities = new ArrayList<>();
        m_EntityComparator = this::compare;
        m_AmbientLight = AmbientLight.create(new Vector3(0.1f, 0.1f, 0.1f));
    
        m_Shader = new Shader();
        m_Shader.addVertexShader("shaders/basic.vert");
        m_Shader.addFragmentShader("shaders/basic.frag");
        m_Shader.compile();
    
        m_CollisionManager = new CollisionManager();
    }
    
    public void addTile(TileBase tile)
    {
        m_Tiles.add(tile);
        tile.setParent(this);
    }
    
    public void removeTile(TileBase tile)
    {
        m_Tiles.remove(tile);
        tile.setParent(null);
    }
    
    public void addLight(Light light)
    {
        m_Lights.add(light);
        light.parent = this;
    }
    
    public void removeLight(Light light)
    {
        m_Lights.remove(light);
        light.parent = null;
    }
    
    public void addEntity(EntityBase entity)
    {
        m_Entities.add(entity);
        entity.setParent(this);
    }
    
    public void removeEntity(EntityBase entity)
    {
        m_Entities.remove(entity);
        entity.setParent(null);
    }
    
    public void setAmbientLight(Vector3 color)
    {
        m_AmbientLight.setColor(color);
    }
    
    public void makeCurrent()
    {
        m_ActiveLevel = this;
    }
    
    @Override
    public void onDestroy()
    {
        m_AmbientLight.destroy();
        m_Shader.destroy();
        
        m_Tiles.clear();
        m_Lights.clear();
        m_Entities.clear();
        
        m_Tiles = null;
        m_Lights = null;
        m_Entities = null;
        m_EntityComparator = null;
        m_AmbientLight = null;
        m_Shader = null;
    }
    
    @Override
    public void removeChild(GameObject gameObject)
    {
        if (gameObject instanceof TileBase)
        {
            this.removeTile((TileBase) gameObject);
        }
        else if (gameObject instanceof Light)
        {
            this.removeLight((Light) gameObject);
        }
        else if (gameObject instanceof EntityBase)
        {
            this.removeEntity((EntityBase) gameObject);
        }
    }
    
    private void doCollisionCheck()
    {
        List<GameObject> gameObjects = new ArrayList<>();
        for (TileBase tile : m_Tiles)
        {
            if (tile instanceof Tile)
            {
                gameObjects.add((Tile) tile);
            }
            else if (tile instanceof AnimatedTile)
            {
                gameObjects.add((AnimatedTile) tile);
            }
        }
        for (EntityBase entity : m_Entities)
        {
            if (entity instanceof Entity)
            {
                gameObjects.add((Entity) entity);
            }
            else if (entity instanceof AnimatedEntity)
            {
                gameObjects.add((AnimatedEntity) entity);
            }
        }
        m_CollisionManager.checkForCollisions(gameObjects);
    }
    
    @Override
    public void update()
    {
        m_Shader.enable();
        m_Tiles.forEach(TileBase::update);
        m_Lights.forEach(Light::update);
        m_Entities.sort(m_EntityComparator);
        m_Entities.forEach(EntityBase::update);
        m_Shader.disable();
    }
    
    @Override
    public void render()
    {
        m_Shader.enable();
        m_Tiles.forEach(TileBase::render);
        m_Lights.forEach(Light::render);
        m_Entities.sort(m_EntityComparator);
        m_Entities.forEach(EntityBase::render);
        m_Shader.disable();
    }
    
    private int compare(EntityBase e1, EntityBase e2)
    {
        if (e1.getPosition().getY() < e2.getPosition().getY())
        {
            return -1;
        }
        return 1;
    }
    
    public static Level getActiveLevel()
    {
        return m_ActiveLevel;
    }
}
