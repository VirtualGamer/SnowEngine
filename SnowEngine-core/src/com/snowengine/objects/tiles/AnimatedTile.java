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
package com.snowengine.objects.tiles;

import com.snowengine.graphics.Texture;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.GameObject;
import com.snowengine.objects.Level;
import com.snowengine.objects.entities.EntityBase;
import com.snowengine.objects.sprites.AnimatedSprite;

import java.util.ArrayList;
import java.util.List;

public class AnimatedTile extends AnimatedSprite implements TileBase
{
    public AnimatedTile(String filepath, int columns, int rows)
    {
        super (filepath, columns, rows);
    }
    
    public AnimatedTile(Texture[] textures)
    {
        super (textures);
    }
    
    @Override
    public void setParent(GameObject parent)
    {
        this.parent = parent;
    }
    
    @Override
    public void destroy()
    {
        super.destroy();
    }
    
    @Override
    public void onCollision(GameObject other)
    {
        super.onCollision(other);
    }
    
    @Override
    public void update()
    {
        super.update();
    
        if (this.parent instanceof Level)
        {
            this.checkForCollisions((Level) this.parent);
        }
    }
    
    @Override
    public void render()
    {
        super.render();
    }
    
    private void checkForCollisions(Level level)
    {
        List<TileBase> tiles = new ArrayList<>();
        List<EntityBase> entities = new ArrayList<>();
        
        for (TileBase tile : level.getTiles())
        {
            if (tile instanceof GameObject)
            {
                if (((GameObject) tile).isColliding(this))
                {
                    tiles.add(tile);
                }
            }
        }
        
        for (EntityBase entity : level.getEntities())
        {
            if (entity instanceof GameObject)
            {
                if (((GameObject) entity).isColliding(this))
                {
                    entities.add(entity);
                }
            }
        }
        
        this.checkForTileCollision(tiles);
        this.checkForEntityCollision(entities);
    }
    
    private void checkForTileCollision(List<TileBase> tiles)
    {
        for (TileBase tile : tiles)
        {
            if (tile != null && tile != this)
            {
                if (tile instanceof GameObject)
                {
                    this.onCollision((GameObject) tile);
                }
            }
        }
    }
    
    private void checkForEntityCollision(List<EntityBase> entities)
    {
        for (EntityBase entity : entities)
        {
            if (entity != null && entity != this)
            {
                if (entity instanceof GameObject)
                {
                    this.onCollision((GameObject) entity);
                }
            }
        }
    }
    
    @Override
    public Vector2 getPosition()
    {
        Vector3 pos = this.transform.getPosition();
        return new Vector2(pos.x, pos.y);
    }
    
    @Override
    public boolean isSolid()
    {
        return false;
    }
}
