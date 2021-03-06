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
package com.snowengine.objects.entities;

import com.snowengine.graphics.Texture;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.GameObject;
import com.snowengine.objects.Level;
import com.snowengine.objects.sprites.AnimatedSprite;
import com.snowengine.objects.tiles.TileBase;

import java.util.ArrayList;
import java.util.List;

public class AnimatedEntity extends AnimatedSprite implements EntityBase
{
    public AnimatedEntity(String filepath, int columns, int rows)
    {
        super (filepath, columns, rows);
    }
    
    public AnimatedEntity(Texture[] textures)
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
    public void move(Vector2 vector)
    {
        super.move(vector);
    }
    
    @Override
    public void scale(Vector2 vector)
    {
        super.scale(vector);
    }
    
    @Override
    public void update()
    {
        super.update();
    }
    
    @Override
    public void render()
    {
        super.render();
    }
    
    @Override
    public Vector2 getPosition()
    {
        Vector3 pos = this.transform.getPosition();
        return new Vector2(pos.x, pos.y);
    }
    
    @Override
    public Vector2 getScale()
    {
        Vector3 scale = this.transform.getScale();
        return new Vector2(scale.x, scale.y);
    }
    
    @Override
    public boolean isSolid()
    {
        return false;
    }
    
    @Override
    public AnimatedEntity copy()
    {
        Texture textures[] = new Texture[this.getTextures().length];
    
        for (int i = 0; i < textures.length; i++)
        {
            textures[i] = this.getTextures()[i].copy();
        }
        
        AnimatedEntity result = new AnimatedEntity(textures);
        result.move(this.transform.getPosition());
        
        result.transform.getScale().x = this.transform.getScale().x;
        result.transform.getScale().y = this.transform.getScale().y;
        result.transform.getScale().z = this.transform.getScale().z;
        
        return result;
    }
}
