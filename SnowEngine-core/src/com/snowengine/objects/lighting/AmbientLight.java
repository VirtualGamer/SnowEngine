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
package com.snowengine.objects.lighting;

import com.snowengine.graphics.Shader;
import com.snowengine.maths.Vector4;
import com.snowengine.objects.GameObject;

public final class AmbientLight extends GameObject
{
    private static AmbientLight m_AmbientLight;
    private Vector4 m_Color;
    
    private AmbientLight(Vector4 color)
    {
        super ("AmbientLight", 10);
        m_AmbientLight = this;
        m_Color = color;
    }
    
    public void setColor(Vector4 color)
    {
        m_Color = color;
    }
    
    @Override
    public void render()
    {
        Shader shader = Shader.getActiveShader();
        shader.setUniform4f("ambientLight", m_Color);
    }
    
    public static AmbientLight create(Vector4 color)
    {
        if (m_AmbientLight != null)
        {
            m_AmbientLight.setColor(color);
            return m_AmbientLight;
        }
        return new AmbientLight(color);
    }
    
    public static AmbientLight getAmbientLight()
    {
        return m_AmbientLight;
    }
}
