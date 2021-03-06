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

import com.snowengine.objects.GameObject;

public final class Canvas extends GameObject
{
    private GUILayer m_Layer;
    
    public Canvas()
    {
        super ("Canvas", 0);
        m_Layer = new GUILayer();
    }
    
    public void drawImage(GUIContainer container)
    {
        m_Layer.add(container);
    }
    
    public void draw(GUIText guiText)
    {
        if (guiText == null)
        {
            return;
        }
        m_Layer.add(guiText);
    }
    
    @Override
    public void update()
    {
        m_Layer.update();
    }
    
    @Override
    public void render()
    {
        m_Layer.render();
    }
}
