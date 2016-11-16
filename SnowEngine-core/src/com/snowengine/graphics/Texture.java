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

import com.snowengine.utils.FileUtils;
import com.snowengine.utils.files.ImageFile;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public final class Texture
{
    private static Texture m_ActiveTexture = null;
    private int m_TextureID, m_Width, m_Height;

    public Texture(String filepath)
    {
        m_TextureID = glGenTextures();

        ImageFile file = FileUtils.openImageFile(filepath);
        m_Width = file.getWidth();
        m_Height = file.getHeight();
        
        glBindTexture(GL_TEXTURE_2D, m_TextureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, m_Width, m_Height, 0, GL_RGBA, GL_UNSIGNED_BYTE, file.getPixels());
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    
    public void bind()
    {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, m_TextureID);
        m_ActiveTexture = this;
    }
    
    public void unbind()
    {
        glBindTexture(GL_TEXTURE_2D, 0);
        m_ActiveTexture = null;
    }
    
    public int getWidth()
    {
        return m_Width;
    }
    
    public int getHeight()
    {
        return m_Height;
    }
    
    public boolean isActive()
    {
        return (m_ActiveTexture == this);
    }
    
    public static Texture getActiveTexture()
    {
        return m_ActiveTexture;
    }
}
