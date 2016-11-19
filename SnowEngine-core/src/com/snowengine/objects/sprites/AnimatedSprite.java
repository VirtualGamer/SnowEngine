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
package com.snowengine.objects.sprites;

import com.snowengine.graphics.Mesh;
import com.snowengine.graphics.Texture;
import com.snowengine.maths.Vector2;
import com.snowengine.objects.colliders.BoxCollider;
import com.snowengine.objects.GameObject;

public class AnimatedSprite extends GameObject
{
    private Mesh m_Mesh;
    private Texture m_Texture[];
    private Vector2 m_Bounds, m_BoundsOffset;
    private int m_FrameIndex;
    
    public AnimatedSprite(String filepath, int columns, int rows)
    {
        this (Texture.splitTextures(filepath, columns, rows));
    }
    
    public AnimatedSprite(Texture[] textures)
    {
        super ("AnimatedSprite", 13);
        m_Mesh = new Mesh();
        m_Texture = textures;
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
    
        m_Bounds = new Vector2(x * 2, y * 2);
        m_BoundsOffset = new Vector2();
    }
    
    @Override
    protected BoxCollider createCollider()
    {
        return new BoxCollider();
    }
    
    protected void setBounds(float width, float height)
    {
        m_Bounds = new Vector2(width, height);
    }
    
    protected void setBounds(float x, float y, float width, float height)
    {
        this.setBoundsOffset(x, y);
        this.setBounds(width, height);
    }
    
    protected void setBoundsOffset(float x, float y)
    {
        m_BoundsOffset = new Vector2(x, y);
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
        float x = this.transform.getPosition().getX() + m_BoundsOffset.getX();
        float y = this.transform.getPosition().getY() + m_BoundsOffset.getY();
        float w = m_Bounds.getX();
        float h = m_Bounds.getY();
        return new Vector2[] {
                new Vector2(x - w, y - h),
                new Vector2(x - w, y + h),
                new Vector2(x + w, y + h),
                new Vector2(x + w, y - h)
        };
    }
    
    public Vector2 getSize()
    {
        return new Vector2(m_Texture[0].getWidth(), m_Texture[0].getHeight());
    }
}
