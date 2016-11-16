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
package com.snowengine.input;

import com.snowengine.maths.Vector2;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public final class Mouse
{
    private static Mouse m_Instance;
    private boolean m_Buttons[], m_ButtonsPressed[];
    private Vector2 m_Position;

    public Mouse()
    {
        if (m_Instance != null)
        {
            throw new RuntimeException("The mouse class is only available through static methods!");
        }
        m_Instance = this;
        m_Buttons = new boolean[GLFW_MOUSE_BUTTON_LAST + 1];
        m_ButtonsPressed = new boolean[GLFW_MOUSE_BUTTON_LAST + 1];
        m_Position = new Vector2();
    }

    public void postButtonAction(int button, int action)
    {
        m_Buttons[button] = (action != GLFW_RELEASE);
    }

    public void postCursorPosition(float xpos, float ypos)
    {
        m_Position.x = xpos;
        m_Position.y = ypos;
    }

    public void update()
    {
        for (int i = 0; i < m_Buttons.length; i++)
        {
            m_ButtonsPressed[i] = GetButton(i);
        }
    }

    public boolean GetButton(int button)
    {
        return button < m_Buttons.length && m_Buttons[button];
    }

    public boolean GetButtonPressed(int button)
    {
        return GetButton(button) && button < m_ButtonsPressed.length && !m_ButtonsPressed[button];
    }

    public boolean GetButtonReleased(int button)
    {
        return !GetButton(button) && button < m_ButtonsPressed.length && m_ButtonsPressed[button];
    }

    public Vector2 GetPosition()
    {
        return m_Position;
    }

    public static boolean getButton(ButtonCode button)
    {
        return m_Instance.GetButton(button.getButtonCode());
    }

    public static boolean getButtonPressed(ButtonCode button)
    {
        return m_Instance.GetButtonPressed(button.getButtonCode());
    }

    public static boolean getButtonReleased(ButtonCode button)
    {
        return m_Instance.GetButtonReleased(button.getButtonCode());
    }

    public static Vector2 getPosition()
    {
        return m_Instance.GetPosition();
    }
}
