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

import com.snowengine.graphics.Shader;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.Camera2D;
import com.snowengine.objects.GameObject;

public final class Canvas extends GameObject
{
    public GameObject owner;
    private Font m_Font;
    
    public Canvas()
    {
        super ("Canvas", 0);
        m_Font = new Font("fonts/test_font.png");
    }
    
    public void drawString(String string, Vector2 offset)
    {
        Vector2 pos = new Vector2();
        if (this.owner != null)
        {
            pos = new Vector2(this.owner.transform.getPosition().getX(), this.owner.transform.getPosition().getY());
        }
        m_Font.drawString(string, offset.add(pos.add(offset)), true);
    }
    
    @Override
    public void update()
    {
        if (this.owner != null)
        {
            Vector3 pos = this.transform.getPosition();
            Vector3 cpos = this.owner.transform.getPosition();
            cpos.x = pos.x;
            cpos.y = pos.y;
            cpos.z = pos.z;
        }
        
        super.update();
    }
    
    @Override
    public void render()
    {
        super.render();
        
        m_Font.render();
    }
}
