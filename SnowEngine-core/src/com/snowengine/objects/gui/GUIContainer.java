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
import com.snowengine.graphics.Window;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.GameObject;
import com.snowengine.objects.sprites.Sprite;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects.gui</package>
 * <class>GUIContainer</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public class GUIContainer extends GameObject
{
    private Sprite m_Sprite;
    
    public GUIContainer(String filepath)
    {
        this (new Texture(filepath));
    }
    
    public GUIContainer(String filepath, Vector2 offset)
    {
        this (new Texture(filepath), offset);
    }
    
    public GUIContainer(Texture texture)
    {
        this (texture, new Vector2());
    }
    
    public GUIContainer(Texture texture, Vector2 offset)
    {
        super ("GUIContainer", 0);
        if (texture != null && offset != null)
        {
            m_Sprite = new Sprite(texture);
            offset.add(m_Sprite.getSize().divide(2));
            m_Sprite.move(new Vector3(offset.x, offset.y, 0));
        }
    }
    
    @Override
    public void render()
    {
        if (m_Sprite != null)
        {
            m_Sprite.render();
        }
    }
}
