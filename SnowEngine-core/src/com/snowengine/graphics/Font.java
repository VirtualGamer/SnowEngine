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
import org.lwjgl.BufferUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.system.MemoryUtil.*;

public abstract class Font
{
    private final String m_Text;
    private final int m_LineCount;
    private int m_FontHeight, m_Scale, m_LineOffset;
    private float m_LineHeight;
    
    public Font(String filepath, int fontSize)
    {
        m_FontHeight = fontSize;
        m_LineHeight = fontSize;
    
        String t;
        int lc;
    
        ByteBuffer source = load(filepath, 4 * 1024);
        if (source != null)
        {
            t = memUTF8(source).replaceAll("\t", "    "); // Replace tabs
        
            lc = 0;
            Matcher m = Pattern.compile("^.*$", Pattern.MULTILINE).matcher(t);
            while ( m.find() )
                lc++;
        }
        else
        {
            t = "Failed to load text.";
            lc = 1;
        }
    
        m_Text = t;
        m_LineCount = lc;
    }
    
    
    public void setScale(int scale) {
        m_Scale = Math.max(-3, scale);
        m_LineHeight = m_FontHeight * (1.0f + m_Scale * 0.25f);
        setLineOffset(m_LineOffset);
    }
    
    public void setLineOffset(float offset) {
        setLineOffset(Math.round(offset));
    }
    
    public void setLineOffset(int offset)
    {
        m_LineOffset = (int) Math.max(0, Math.min(offset, m_LineCount - m_LineHeight));
    }
    
    public String getText()
    {
        return m_Text;
    }
    
    public int getFontHeight()
    {
        return m_FontHeight;
    }
    
    public int getScale()
    {
        return m_Scale;
    }
    
    public int getLineOffset()
    {
        return m_LineOffset;
    }
    
    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }
    
    protected static ByteBuffer load(String filepath, int bufferSize)
    {
        filepath = FileUtils.getPathPrefix() + filepath;
    
        try
        {
            ByteBuffer buffer;
            Path path = Paths.get(filepath);
            if ( Files.isReadable(path) ) {
                try (SeekableByteChannel fc = Files.newByteChannel(path))
                {
                    buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);
                    while ( fc.read(buffer) != -1 );
                }
            }
            else
            {
                try (
                            InputStream source = new FileInputStream(filepath);
                            ReadableByteChannel rbc = Channels.newChannel(source)
                )
                {
                    buffer = createByteBuffer(bufferSize);
            
                    while ( true )
                    {
                        int bytes = rbc.read(buffer);
                        if ( bytes == -1 )
                            break;
                        if ( buffer.remaining() == 0 )
                            buffer = resizeBuffer(buffer, buffer.capacity() * 2);
                    }
                }
            }
    
            buffer.flip();
            return buffer;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
