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
public class Sprite extends GameObject
{
    private Mesh m_Mesh;
    private Texture m_Texture;
    
    public Sprite(String filepath)
    {
        super ("Sprite", 0);
        m_Texture = new Texture(filepath);
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
        float x = m_Texture.getWidth() / 2;
        float y = m_Texture.getHeight() / 2;
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
