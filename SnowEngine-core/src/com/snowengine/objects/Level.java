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
import com.snowengine.objects.entities.EntityBase;
import com.snowengine.objects.lighting.AmbientLight;
import com.snowengine.objects.lighting.Light;
import com.snowengine.objects.tiles.TileBase;

import java.util.ArrayList;
import java.util.List;

public final class Level extends GameObject
{
    private List<TileBase> m_Tiles;
    private List<Light> m_Lights;
    private List<EntityBase> m_Entities;
    private AmbientLight m_AmbientLight;
    private Shader m_Shader;
    
    public Level()
    {
        super ("Level", 9);
        
        m_Tiles = new ArrayList<>();
        m_Entities = new ArrayList<>();
        m_Lights = new ArrayList<>();
        m_AmbientLight = AmbientLight.create(new Vector3(0.1f, 0.1f, 0.1f));
    
        m_Shader = new Shader();
        m_Shader.addVertexShader("shaders/basic.vert");
        m_Shader.addFragmentShader("shaders/basic.frag");
        m_Shader.compile();
    }
    
    public void addTile(TileBase tile)
    {
        m_Tiles.add(tile);
    }
    
    public void addLight(Light light)
    {
        m_Lights.add(light);
    }
    
    public void addEntity(EntityBase entity)
    {
        m_Entities.add(entity);
    }
    
    public void setAmbientLight(Vector3 color)
    {
        m_AmbientLight.setColor(color);
    }
    
    @Override
    public void update()
    {
        m_Shader.enable();
        m_Tiles.forEach(TileBase::update);
        m_Lights.forEach(Light::update);
        m_Shader.disable();
    }
    
    @Override
    public void render()
    {
        m_Shader.enable();
        m_Tiles.forEach(TileBase::render);
        m_Lights.forEach(Light::render);
        m_Shader.disable();
    }
}
