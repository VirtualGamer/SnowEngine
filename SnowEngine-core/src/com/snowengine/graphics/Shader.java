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

import com.snowengine.maths.Matrix4;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.maths.Vector4;
import com.snowengine.utils.FileUtils;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public final class Shader
{
    private static Shader m_ActiveShader = null;
    private int m_ShaderID;
    private List<Integer> m_Shaders;
    private Map<String, Integer> m_UniformCache;

    public Shader()
    {
        m_ShaderID = glCreateProgram();
        m_Shaders = new ArrayList<>();
        m_UniformCache = new HashMap<>();
    }

    public void destroy()
    {
        glDeleteProgram(m_ShaderID);
    }

    public void addVertexShader(String filepath)
    {
        this.addShader(GL_VERTEX_SHADER, filepath);
    }

    public void addFragmentShader(String filepath)
    {
        this.addShader(GL_FRAGMENT_SHADER, filepath);
    }

    public void addGeometryShader(String filepath)
    {
        this.addShader(GL_GEOMETRY_SHADER, filepath);
    }

    private void addShader(int type, String filepath)
    {
        int shader = glCreateShader(type);
        String source = FileUtils.openTextFile(filepath).getSource();
        glShaderSource(shader, source);
        glCompileShader(shader);

        if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE)
        {
            System.out.println("Failed to compile the shader source (" + filepath + ")");
            System.out.println(glGetShaderInfoLog(shader));
            glDeleteShader(shader);
            return;
        }

        glAttachShader(m_ShaderID, shader);
        m_Shaders.add(shader);
    }

    public void compile()
    {
        glLinkProgram(m_ShaderID);
        glValidateProgram(m_ShaderID);
        m_Shaders.forEach(GL20::glDeleteShader);
    }

    public void enable()
    {
        glUseProgram(m_ShaderID);
        m_ActiveShader = this;
    }

    public void disable()
    {
        glUseProgram(0);
        m_ActiveShader = null;
    }

    private int getUniformLocation(String name)
    {
        if (m_UniformCache.containsKey(name))
        {
            return m_UniformCache.get(name);
        }
        else
        {
            int loc = glGetUniformLocation(m_ShaderID, name);
            m_UniformCache.put(name, loc);
            return loc;
        }
    }

    public void setUniform1i(String name, int value)
    {
        glUniform1i(this.getUniformLocation(name), value);
    }

    public void setUniform1f(String name, float value)
    {
        glUniform1f(this.getUniformLocation(name), value);
    }

    public void setUniform2f(String name, Vector2 vector)
    {
        glUniform2f(this.getUniformLocation(name), vector.x, vector.y);
    }

    public void setUniform3f(String name, Vector3 vector)
    {
        glUniform3f(this.getUniformLocation(name), vector.x, vector.y, vector.z);
    }

    public void setUniform4f(String name, Vector4 vector)
    {
        glUniform4f(this.getUniformLocation(name), vector.x, vector.y, vector.z, vector.w);
    }

    public void setUniformMatrix4f(String name, Matrix4 matrix)
    {
        glUniformMatrix4fv(this.getUniformLocation(name), false, matrix.elements);
    }

    public boolean isActive()
    {
        return (m_ActiveShader == this);
    }

    public static Shader getActiveShader()
    {
        return m_ActiveShader;
    }
}
