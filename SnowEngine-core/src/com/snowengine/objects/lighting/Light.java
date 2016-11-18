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
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.GameObject;

public class Light extends GameObject
{
    private static final int MAX_LIGHTS = 10;
    private static int m_CurrentLightCount = 0;
    private int m_LightID;
    private Vector3 m_Color;
    private float m_Radius;
    
    public Light(Vector2 position, Vector3 color, float radius)
    {
        super ("Light", 11);
        
        if (m_CurrentLightCount >= MAX_LIGHTS)
        {
            System.err.println("Overriding lighting array size!");
            return;
        }
        
        this.move(position);
        m_LightID = m_CurrentLightCount++;
        m_Color = color;
        m_Radius = radius;
    }
    
    public void setColor(Vector3 color)
    {
        m_Color = color;
    }
    
    public void setRadius(float radius)
    {
        m_Radius = radius;
    }
    
    @Override
    public void render()
    {
        super.render();
        
        Shader shader = Shader.getActiveShader();
        String prefix = "lights[" + m_LightID + "]";
        Vector3 pos = this.transform.getPosition();
        shader.setUniform2f(prefix + ".position", new Vector2(pos.x, pos.y));
        shader.setUniform3f(prefix + ".color", m_Color);
        shader.setUniform1f(prefix + ".radius", m_Radius);
    }
    
    public Vector3 getColor()
    {
        return m_Color;
    }
    
    public float getRadius()
    {
        return m_Radius;
    }
}
