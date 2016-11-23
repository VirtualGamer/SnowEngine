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
import com.snowengine.graphics.Window;
import com.snowengine.maths.Matrix4;
import com.snowengine.maths.Vector2;

import java.util.ArrayList;
import java.util.List;

public final class GUILayer
{
    private List<GUIContainer> m_Drawables;
    private Font m_Font;
    private Shader m_Shader;
    private Matrix4 m_Projection;
    
    public GUILayer()
    {
        m_Drawables = new ArrayList<>();
        m_Font = new Font("fonts/test_font.png");
        m_Shader = new Shader();
        m_Shader.addVertexShader("shaders/gui.vert");
        m_Shader.addFragmentShader("shaders/gui.frag");
        m_Shader.compile();
    }
    
    public void add(GUIContainer container)
    {
        m_Drawables.add(container);
    }
    
    public void add(GUIText text)
    {
        Vector2 pos = text.getPosition().copy();
        float w = (float) Window.getActiveWindow().getWidth() / 2;
        pos.add(new Vector2(w, 14));
        m_Font.drawString(text.getText(), pos, true);
    }
    
    public void update()
    {
        m_Shader.enable();
        m_Drawables.forEach(GUIContainer::update);
        m_Shader.disable();
    }
    
    public void render()
    {
        Window window = Window.getActiveWindow();
        float w = (float) window.getWidth(), h = (float) window.getHeight();
        m_Projection = Matrix4.orthographic(0, w, h, 0, -1, 1);
        
        m_Shader.enable();
        m_Shader.setUniformMatrix4f("projection", m_Projection);
        m_Drawables.forEach(GUIContainer::render);
        m_Font.render();
        m_Shader.disable();
        
        m_Drawables.clear();
    }
}
