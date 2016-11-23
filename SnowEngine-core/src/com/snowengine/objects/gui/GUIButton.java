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

import com.snowengine.maths.Vector2;

public final class GUIButton extends GUIContainerAnimated
{
    private boolean m_Selected;
    
    public GUIButton(String filepath)
    {
        this (filepath, new Vector2());
    }
    
    public GUIButton(String filepath, Vector2 pos)
    {
        super (filepath, 2, 1, pos);
        m_Selected = false;
    }
    
    public void setSelected(boolean b)
    {
        m_Selected = b;
    }
    
    @Override
    public void update()
    {
        super.update();
        
        if (m_Selected)
        {
            this.setFrame(1);
        }
        else
        {
            this.setFrame(0);
        }
    }
    
    @Override
    public void render()
    {
        super.render();
    }
    
    public boolean isSelected()
    {
        return m_Selected;
    }
}
