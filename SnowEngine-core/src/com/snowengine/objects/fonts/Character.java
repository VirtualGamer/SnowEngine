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
package com.snowengine.objects.fonts;

import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector4;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects.fonts</package>
 * <class>Character</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public final class Character
{
    private int m_Id;
    private Vector4 m_TexCoords;
    private Vector2 m_Position, m_Size;
    private float m_XAdvance;
    
    protected Character(int id, Vector4 texCoords, Vector2 pos, Vector2 size, float xAdvance)
    {
        m_Id = id;
        m_TexCoords = texCoords;
        m_Position = pos;
        m_Size = size;
        m_XAdvance = xAdvance;
    }
    
    public int getId()
    {
        return m_Id;
    }
    
    public Vector4 getTextureCoordinates()
    {
        return m_TexCoords;
    }
    
    public Vector2 getPosition()
    {
        return m_Position;
    }
    
    public Vector2 getSize()
    {
        return m_Size;
    }
    
    public float getXAdvance()
    {
        return m_XAdvance;
    }
}
