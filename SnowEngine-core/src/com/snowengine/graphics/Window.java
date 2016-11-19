/**
 * Copyright (c) 2016 Mark Rienstra
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.snowengine.graphics;

import com.snowengine.audio.AudioMaster;
import com.snowengine.input.Keyboard;
import com.snowengine.input.Mouse;
import com.snowengine.maths.Vector3;
import com.snowengine.utils.FileUtils;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class Window
{
    private static Window m_ActiveWindow = null;
    private final String m_Title;
    private int m_Width, m_Height;
    private boolean m_FullScreen, m_VSync, m_Visible;
    private long m_Window;

    private WindowSizeCallback m_WindowSizeCallback;
    private Keyboard m_Keyboard;
    private Mouse m_Mouse;

    public Window(String title, int width, int height)
    {
        this (title, width, height, false);
    }

    public Window(String title, int width, int height, boolean fullScreen)
    {
        this (title, width, height, fullScreen, true);
    }

    public Window(String title, int width, int height, boolean fullScreen, boolean vSync)
    {
        m_Title = title;
        m_Width = width;
        m_Height = height;
        m_FullScreen = fullScreen;
        m_VSync = vSync;
        m_Visible = false;

        m_Keyboard = new Keyboard();
        m_Mouse = new Mouse();

        if (!this.init())
        {
            glfwTerminate();
        }
    }

    public void destroy()
    {
        glfwSetCursorPosCallback(m_Window, null);
        glfwSetMouseButtonCallback(m_Window, null);
        glfwSetKeyCallback(m_Window, null);
        glfwSetWindowSizeCallback(m_Window, null);
        glfwDestroyWindow(m_Window);
        FileUtils.closeFiles();
        AudioMaster.terminate();
        glfwTerminate();
    }

    private boolean init()
    {
        m_ActiveWindow = this;
        glfwSetErrorCallback(this::errorCallback);
        AudioMaster.setErrorCallback(this::errorCallback);
        if (!glfwInit())
        {
            System.out.println("Failed to initialize GLFW!");
            return false;
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        long monitor = NULL;
        if (m_FullScreen)
        {
            monitor = glfwGetPrimaryMonitor();
            GLFWVidMode vidMode = glfwGetVideoMode(monitor);
            m_Width = vidMode.width();
            m_Height = vidMode.height();
        }

        m_Window = glfwCreateWindow(m_Width, m_Height, m_Title, monitor, NULL);
        if (m_Window == NULL)
        {
            System.out.println("Failed to create the window!!");
            return false;
        }

        if (!m_FullScreen)
        {
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(m_Window, (vidMode.width() - m_Width) / 2, (vidMode.height() - m_Height) / 2);
        }

        glfwSetWindowSizeCallback(m_Window, this::windowResize);
        glfwSetKeyCallback(m_Window, this::keyCallback);
        glfwSetMouseButtonCallback(m_Window, this::mouseButtonCallback);
        glfwSetCursorPosCallback(m_Window, this::cursorPosCallback);

        glfwMakeContextCurrent(m_Window);
        glfwSwapInterval(m_VSync ? GLFW_TRUE : GLFW_FALSE);

        createCapabilities();

        if (!AudioMaster.init())
        {
            System.out.println("Failed to initialize OpenAL!");
            return false;
        }
    
        System.out.println("LWJGL " + Version.getVersion());
        System.out.println("GLFW " + glfwGetVersionString());
        System.out.println("OpenAL " + AudioMaster.getVersionString());
        System.out.println("OpenCL ??");
        System.out.println("OpenGL " + glGetString(GL_VERSION));
        return true;
    }

    public void setSizeCallback(WindowSizeCallback cbFun)
    {
        m_WindowSizeCallback = cbFun;
    }

    public void setClearColor(Vector3 color)
    {
        glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
    }

    public void setCursor(Cursor cursor)
    {
        glfwSetCursor(m_Window, cursor.getCursorID());
    }
    
    public void showCursor(boolean visible)
    {
        glfwSetInputMode(m_Window, GLFW_CURSOR, visible ? GLFW_CURSOR_NORMAL : GLFW_CURSOR_HIDDEN);
    }
    
    public void setVSync(boolean vSync)
    {
        m_VSync = vSync;
        glfwSwapInterval(m_VSync ? GLFW_TRUE : GLFW_FALSE);
    }

    public void setVisible(boolean visible)
    {
        m_Visible = visible;
        if (m_Visible)
        {
            glfwShowWindow(m_Window);
        }
        else
        {
            glfwHideWindow(m_Window);
        }
    }

    public void requestClose()
    {
        glfwSetWindowShouldClose(m_Window, true);
    }

    public void clear()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void update()
    {
        int error = glGetError();
        if (error != GL_NO_ERROR)
        {
            System.out.println("OpenGL Error (" + error + ")");
        }

        m_Keyboard.update();
        m_Mouse.update();

        glfwSwapBuffers(m_Window);
        glfwPollEvents();
        AudioMaster.poll();
    }

    public String getTitle()
    {
        return m_Title;
    }

    public int getWidth()
    {
        return m_Width;
    }

    public int getHeight()
    {
        return m_Height;
    }

    public boolean isCloseRequested()
    {
        return glfwWindowShouldClose(m_Window);
    }

    public boolean isFullScreen()
    {
        return m_FullScreen;
    }

    public boolean isVSync()
    {
        return m_VSync;
    }

    public boolean isVisible()
    {
        return m_Visible;
    }

    public static Window getActiveWindow()
    {
        return m_ActiveWindow;
    }
    
    private void windowResize(long window, int newWidth, int newHeight)
    {
        m_Width = newWidth;
        m_Height = newHeight;
        glViewport(0, 0, m_Width, m_Height);

        if (m_WindowSizeCallback != null)
        {
            m_WindowSizeCallback.windowResize(m_Width, m_Height);
        }
    }

    private void keyCallback(long window, int key, int scancode, int action, int mods)
    {
        m_Keyboard.postKeyAction(key, action);
    }

    private void mouseButtonCallback(long window, int button, int action, int mods)
    {
        m_Mouse.postButtonAction(button, action);
    }

    private void cursorPosCallback(long window, double xpos, double ypos)
    {
        m_Mouse.postCursorPosition((float) xpos, (float) ypos);
    }

    private void errorCallback(int error, long description)
    {
        System.out.println("GLFW Error: " + GLFWErrorCallback.getDescription(description));
    }

    private void errorCallback(int error, String description)
    {
        System.out.println("OpenAL Error: " + description);
    }

    public interface WindowSizeCallback
    {
        void windowResize(int newWidth, int newHeight);
    }
}
