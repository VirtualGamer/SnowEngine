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

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public final class Keyboard
{
    private static Keyboard m_Instance;
    private boolean m_Keys[], m_KeysPressed[];

    public Keyboard()
    {
        if (m_Instance != null)
        {
            throw new RuntimeException("The keyboard class is only available through static methods!");
        }
        m_Instance = this;
        m_Keys = new boolean[GLFW_KEY_LAST + 1];
        m_KeysPressed = new boolean[GLFW_KEY_LAST + 1];
    }

    public void postKeyAction(int key, int action)
    {
        if (key < 0)
        {
            return;
        }
        m_Keys[key] = (action != GLFW_RELEASE);
    }

    public void update()
    {
        for (int i = 0; i < m_Keys.length; i++)
        {
            m_KeysPressed[i] = GetKey(i);
        }
    }

    public boolean GetKey(int key)
    {
        return key < m_Keys.length && m_Keys[key];
    }

    public boolean GetKeyPressed(int key)
    {
        return GetKey(key) && key < m_KeysPressed.length && !m_KeysPressed[key];
    }

    public boolean GetKeyReleased(int key)
    {
        return !GetKey(key) && key < m_KeysPressed.length && m_KeysPressed[key];
    }

    public static boolean getKey(KeyCode key)
    {
        int keyCode = key.getKeyCode();
        if (keyCode >= 400)
        {
            switch (keyCode)
            {
                case 400:
                    return m_Instance.GetKey(KeyCode.LeftShift.getKeyCode()) || m_Instance.GetKey(KeyCode.RightShift.getKeyCode());
                case 401:
                    return m_Instance.GetKey(KeyCode.LeftControl.getKeyCode()) || m_Instance.GetKey(KeyCode.RightControl.getKeyCode());
                case 402:
                    return m_Instance.GetKey(KeyCode.LeftAlt.getKeyCode()) || m_Instance.GetKey(KeyCode.RightAlt.getKeyCode());
                case 403:
                    return m_Instance.GetKey(KeyCode.LeftSuper.getKeyCode()) || m_Instance.GetKey(KeyCode.RightSuper.getKeyCode());
                default:
                    break;
            }
        }
        return m_Instance.GetKey(keyCode);
    }

    public static boolean getKeyPressed(KeyCode key)
    {
        int keyCode = key.getKeyCode();
        if (keyCode >= 400)
        {
            switch (keyCode)
            {
                case 400:
                    return m_Instance.GetKeyPressed(KeyCode.LeftShift.getKeyCode()) || m_Instance.GetKeyPressed(KeyCode.RightShift.getKeyCode());
                case 401:
                    return m_Instance.GetKeyPressed(KeyCode.LeftControl.getKeyCode()) || m_Instance.GetKeyPressed(KeyCode.RightControl.getKeyCode());
                case 402:
                    return m_Instance.GetKeyPressed(KeyCode.LeftAlt.getKeyCode()) || m_Instance.GetKeyPressed(KeyCode.RightAlt.getKeyCode());
                case 403:
                    return m_Instance.GetKeyPressed(KeyCode.LeftSuper.getKeyCode()) || m_Instance.GetKeyPressed(KeyCode.RightSuper.getKeyCode());
                default:
                    break;
            }
        }
        return m_Instance.GetKeyPressed(keyCode);
    }

    public static boolean getKeyReleased(KeyCode key)
    {
        int keyCode = key.getKeyCode();
        if (keyCode >= 400)
        {
            switch (keyCode)
            {
                case 400:
                    return m_Instance.GetKeyReleased(KeyCode.LeftShift.getKeyCode()) || m_Instance.GetKeyReleased(KeyCode.RightShift.getKeyCode());
                case 401:
                    return m_Instance.GetKeyReleased(KeyCode.LeftControl.getKeyCode()) || m_Instance.GetKeyReleased(KeyCode.RightControl.getKeyCode());
                case 402:
                    return m_Instance.GetKeyReleased(KeyCode.LeftAlt.getKeyCode()) || m_Instance.GetKeyReleased(KeyCode.RightAlt.getKeyCode());
                case 403:
                    return m_Instance.GetKeyReleased(KeyCode.LeftSuper.getKeyCode()) || m_Instance.GetKeyReleased(KeyCode.RightSuper.getKeyCode());
                default:
                    break;
            }
        }
        return m_Instance.GetKeyReleased(keyCode);
    }
}
