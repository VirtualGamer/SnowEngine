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
package com.snowengine.graphics.buffers;

import static org.lwjgl.opengl.GL15.*;

public final class Buffer
{
    private int m_BufferID, m_ComponentCount;

    public Buffer(float data[], int componentCount)
    {
        m_ComponentCount = componentCount;

        m_BufferID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, m_BufferID);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void delete()
    {
        glDeleteBuffers(m_BufferID);
    }

    public void bind()
    {
        glBindBuffer(GL_ARRAY_BUFFER, m_BufferID);
    }

    public void unbind()
    {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public int getComponentCount()
    {
        return m_ComponentCount;
    }
}
