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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public final class Gamepad
{
    private static Map<Integer, Gamepad> m_Gamepads;

    static
    {
        m_Gamepads = new HashMap<>();
        glfwSetJoystickCallback(Gamepad::gamepadConnect);

        for (int gp = GLFW_JOYSTICK_1; gp <= GLFW_JOYSTICK_LAST; gp++)
        {
            Gamepad gamepad = new Gamepad(gp);
            gamepad.setConnected(glfwJoystickPresent(gp));
            m_Gamepads.put(gp, gamepad);
        }
    }

    private int m_GamepadID;
    private boolean m_Connected;

    private Gamepad(int gamepadId)
    {
        m_GamepadID = gamepadId;
        m_Connected = true;
    }

    private void setConnected(boolean connected)
    {
        m_Connected = connected;
    }

    public boolean isConnected()
    {
        return m_Connected;
    }

    public static Gamepad getGamepadById(int id)
    {
        if (id > GLFW_JOYSTICK_LAST)
        {
            throw new IllegalArgumentException("Invalid id (" + id + "), maximum amount of gamepads is 16!\nNote: the id is minus 1!");
        }
        return m_Gamepads.get(id);
    }

    public static Gamepad[] getConnectedGamepads()
    {
        List<Gamepad> gamepads = new ArrayList<>();
        m_Gamepads.values().forEach(gp -> {
            if (gp.isConnected())
            {
                gamepads.add(gp);
            }
        });
        return gamepads.toArray(new Gamepad[gamepads.size()]);
    }

    private static void gamepadConnect(int gamepadId, int connected)
    {
        m_Gamepads.get(gamepadId).setConnected(connected == GLFW_CONNECTED);
    }
}
