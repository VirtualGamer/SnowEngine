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

public class Sprite extends GameObject
{
    private Mesh m_Mesh;
    private Texture m_Texture;
    
    public Sprite(String filepath)
    {
        this (new Texture(filepath));
    }
    
    public Sprite(Texture texture)
    {
        super ("Sprite", 0);
        m_Texture = texture;
        m_Mesh = new Mesh();
        
        float x = m_Texture.getWidth() / 2;
        float y = m_Texture.getHeight() / 2;
        
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
        
        m_Texture.bind();
        m_Mesh.bind();
        m_Mesh.draw();
        m_Mesh.unbind();
        m_Texture.unbind();
    }
    
    public Vector2[] getExtents()
    {
        float x = this.transform.getPosition().getX();
        float y = this.transform.getPosition().getY();
        float w = m_Texture.getWidth() / 2;
        float h = m_Texture.getHeight() / 2;
        return new Vector2[] {
            new Vector2(x - w, y - h),
            new Vector2(x - w, y + h),
            new Vector2(x + w, y + h),
            new Vector2(x + w, y - h)
        };
    }
}
