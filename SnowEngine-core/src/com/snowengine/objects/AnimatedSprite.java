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

import com.snowengine.graphics.Mesh;
import com.snowengine.graphics.Texture;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects</package>
 * <class>Sprite</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public class AnimatedSprite extends GameObject
{
    private Mesh m_Mesh;
    private Texture m_Texture[];
    private int m_FrameIndex;
    
    public AnimatedSprite(String filepath, int columns, int rows)
    {
        super ("Sprite", 0);
        m_Mesh = new Mesh();
        m_Texture = Texture.splitTextures(filepath, columns, rows);
        m_FrameIndex = 0;
        
        float x = m_Texture[0].getWidth() / 2;
        float y = m_Texture[0].getHeight() / 2;
        
        float vertices[] =
        {
           -x,-y, 0,
           -x, y, 0,
            x, y, 0,
            x,-y, 0
        };
        
        float uvs[] =
        {
            0, 0,
            0, 1,
            1, 1,
            1, 0
        };
        
        short indices[] =
        {
            0, 1, 2,
            2, 3, 0
        };
        
        m_Mesh.setMeshData(vertices, uvs, indices, true);
    }
    
    @Override
    protected BoxCollider createCollider()
    {
        return new BoxCollider();
    }
    
    public void setFrame(int index)
    {
        m_FrameIndex = index;
    }
    
    
    @Override
    public void move(Vector2 vector)
    {
        super.move(vector);
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
        
        if (m_FrameIndex >= m_Texture.length)
        {
            m_FrameIndex = 0;
        }
        
        m_Texture[m_FrameIndex].bind();
        m_Mesh.bind();
        m_Mesh.draw();
        m_Mesh.unbind();
        m_Texture[m_FrameIndex].unbind();
    }
    
    public Vector2[] getExtents()
    {
        float x = m_Texture[0].getWidth() / 2;
        float y = m_Texture[0].getHeight() / 2;
        x += this.transform.getPosition().getX();
        y += this.transform.getPosition().getY();
        Vector2[] extents =
                {
                        new Vector2(-x,-y),
                        new Vector2(-x, y),
                        new Vector2( x, y),
                        new Vector2( x,-y)
                };
        return extents;
    }
}
