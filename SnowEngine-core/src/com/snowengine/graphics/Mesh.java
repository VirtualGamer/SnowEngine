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
package com.snowengine.graphics;

import com.snowengine.graphics.buffers.Buffer;
import com.snowengine.graphics.buffers.IndexBuffer;
import com.snowengine.graphics.buffers.VertexArray;

import static org.lwjgl.opengl.GL11.*;

public final class Mesh
{
    private VertexArray m_VAO;
    private IndexBuffer m_IBO;
    
    public Mesh()
    {
        m_VAO = new VertexArray();
    }
    
    public void setMeshData(float vertices[], short indices[])
    {
        m_VAO.addBuffer(new Buffer(vertices, 3), 0);
        m_IBO = new IndexBuffer(indices, indices.length);
    }
    
    public void setMeshData(float vertices[], float colors[], short indices[])
    {
        m_VAO.addBuffer(new Buffer(vertices, 3), 0);
        m_VAO.addBuffer(new Buffer(colors, 4), 1);
        m_IBO = new IndexBuffer(indices, indices.length);
    }
    
    public void bind()
    {
        m_VAO.bind();
        m_IBO.bind();
    }
    
    public void unbind()
    {
        m_VAO.unbind();
        m_IBO.unbind();
    }
    
    public void draw()
    {
        glDrawElements(GL_TRIANGLES, m_IBO.getCount(), GL_UNSIGNED_SHORT, 0);
    }
}
