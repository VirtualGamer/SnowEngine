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
package com.snowengine.objects.gui;

import com.snowengine.graphics.Texture;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.GameObject;
import com.snowengine.objects.sprites.AnimatedSprite;
import com.snowengine.objects.sprites.Sprite;

public class GUIContainerAnimated extends GUIContainer
{
    private AnimatedSprite m_Sprite;
    
    public GUIContainerAnimated(String filepath, int cols, int rows)
    {
        this (Texture.splitTextures(filepath, cols, rows), new Vector2());
    }
    
    public GUIContainerAnimated(String filepath, int cols, int rows, Vector2 offset)
    {
        this (Texture.splitTextures(filepath, cols, rows), offset);
    }
    
    public GUIContainerAnimated(Texture[] textures)
    {
        this (textures, new Vector2());
    }
    
    public GUIContainerAnimated(Texture[] textures, Vector2 offset)
    {
        super ((Texture) null, null);
        m_Sprite = new AnimatedSprite(textures);
        offset.add(m_Sprite.getSize().divide(2));
        m_Sprite.move(new Vector3(offset.x, offset.y, 0));
    }
    
    public void setFrame(int index)
    {
        m_Sprite.setFrame(index);
    }
    
    @Override
    public void render()
    {
        m_Sprite.render();
    }
}
