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
import com.snowengine.objects.sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Sprite implements TileBase
{
    private Texture m_Texture;
    
    public Tile(String filepath)
    {
        super (filepath);
        m_Texture = new Texture(filepath);
    }
    
    public Tile(Texture texture)
    {
        super (texture);
        m_Texture = texture;
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
    public boolean isSolid()
    {
        return false;
    }
    
    public Tile copy()
    {
        return new Tile(m_Texture.copy());
    }
}
