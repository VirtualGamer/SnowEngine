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
package com.snowengine.utils.files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public final class ImageData extends FileData
{
    private int m_Width, m_Height, m_Pixels[];

    protected ImageData(String filepath)
    {
        super (filepath);
    }

    @Override
    protected void load()
    {
        try
        {
            BufferedImage image = ImageIO.read(new FileInputStream(m_FilePath));
            m_Width = image.getWidth();
            m_Height = image.getHeight();
            int pixels[] = new int[m_Width * m_Height];
            image.getRGB(0, 0, m_Width, m_Height, pixels, 0, m_Width);
            image.flush();

            m_Pixels = new int[pixels.length];
            for (int i = 0; i < pixels.length; i++)
            {
                int a = (pixels[i] & 0xff000000) >> 24;
                int r = (pixels[i] & 0xff0000) >> 16;
                int g = (pixels[i] & 0xff00) >> 8;
                int b = (pixels[i] & 0xff);
                m_Pixels[i] = a << 24 | b << 16 | g << 8 | r;
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public int getWidth()
    {
        return m_Width;
    }

    public int getHeight()
    {
        return m_Height;
    }

    public int[] getPixels()
    {
        int array[] = new int[m_Pixels.length];
        System.arraycopy(m_Pixels, 0, array, 0, m_Pixels.length);
        return array;
    }
}
