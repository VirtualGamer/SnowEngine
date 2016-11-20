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
package com.snowengine.objects.gui;

import com.snowengine.maths.Vector2;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects.gui</package>
 * <class>GUIText</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public final class GUIText
{
    private String m_Text;
    private Vector2 m_Position;
    
    public GUIText(String text)
    {
        this (text, new Vector2());
    }
    
    public GUIText(String text, Vector2 position)
    {
        m_Text = text;
        m_Position = position;
    }
    
    public void setText(String text)
    {
        m_Text = text;
    }
    
    public void setPosition(Vector2 position)
    {
        m_Position = position;
    }
    
    public String getText()
    {
        return m_Text;
    }
    
    public Vector2 getPosition()
    {
        return m_Position;
    }
}
