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
package com.snowengine.objects;

import com.snowengine.audio.AudioListener;
import com.snowengine.graphics.Shader;
import com.snowengine.graphics.Window;
import com.snowengine.maths.Matrix4;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects</package>
 * <class>Camera2D</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public final class Camera2D extends GameObject
{
    private Matrix4 m_Projection, m_ViewMatrix;
    private AudioListener m_Listener;
    
    public Camera2D()
    {
        super ("Camera2D", 2);
        Window window = Window.getActiveWindow();
        window.setSizeCallback(this::windowResize);
    
        float halfWidth = window.getWidth() / 2.0f;
        float halfHeight = window.getHeight() / 2.0f;
        m_Projection = Matrix4.orthographic(-halfWidth, halfWidth, halfHeight, -halfHeight, -1, 1);
        m_ViewMatrix = Matrix4.identity();
    
        m_Listener = new AudioListener();
    }
    
    @Override
    public void update()
    {
        m_Listener.setPosition(this.transform.getPosition());
    
        m_ViewMatrix = Matrix4.translate(this.transform.getPosition().negate())
                              .multiply(this.transform.getRotationMatrix()
                                                      .multiply(this.transform.getScaleMatrix()));
        
        super.update();
    }
    
    @Override
    public void render()
    {
        Shader shader = Shader.getActiveShader();
        shader.setUniformMatrix4f("projection", m_Projection);
        shader.setUniformMatrix4f("view", m_ViewMatrix);
        
        super.render();
    }
    
    public Matrix4 getProjection()
    {
        return m_Projection;
    }
    
    public Matrix4 getViewMatrix()
    {
        return m_ViewMatrix;
    }
    
    public AudioListener getAudioListener()
    {
        return m_Listener;
    }
    
    private void windowResize(int newWidth, int newHeight)
    {
        float halfWidth = newWidth / 2.0f;
        float halfHeight = newHeight / 2.0f;
        m_Projection = Matrix4.orthographic(-halfWidth, halfWidth, halfHeight, -halfHeight, -1, 1);
    }
}
