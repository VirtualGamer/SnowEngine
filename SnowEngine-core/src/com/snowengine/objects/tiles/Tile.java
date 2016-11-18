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
import com.snowengine.objects.GameObject;
import com.snowengine.objects.sprites.Sprite;

public class Tile extends Sprite implements TileBase
{
    public Tile(String filepath)
    {
        super (filepath);
    }
    
    public Tile(Texture texture)
    {
        super (texture);
    }
    
    @Override
    public void setParent(GameObject parent)
    {
        this.parent = parent;
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
    public boolean isSolid()
    {
        return false;
    }
}
