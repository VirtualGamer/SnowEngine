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

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public final class VertexArray
{
    private int m_ArrayID;
    private List<Buffer> m_Buffers;

    public VertexArray()
    {
        m_ArrayID = glGenVertexArrays();
        m_Buffers = new ArrayList<>();
    }

    public void delete()
    {
        m_Buffers.forEach(Buffer::delete);
        glDeleteVertexArrays(m_ArrayID);
    }

    public void addBuffer(Buffer buffer, int index)
    {
        bind();
        buffer.bind();

        glEnableVertexAttribArray(index);
        glVertexAttribPointer(index, buffer.getComponentCount(), GL_FLOAT, false, 0, 0);

        buffer.unbind();
        unbind();


        m_Buffers.add(buffer);
    }

    public void bind()
    {
        glBindVertexArray(m_ArrayID);
    }

    public void unbind()
    {
        glBindVertexArray(0);
    }
}
