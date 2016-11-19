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

import com.snowengine.maths.Vector2;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBTruetype.*;
import static org.lwjgl.system.MemoryStack.*;

public final class TTFont extends Font
{
    private String m_Filepath;
    private int m_BitmapWidth, m_BitmapHeight;
    private STBTTBakedChar.Buffer m_Data;
    
    public TTFont(String filepath, int fontSize, int width, int height)
    {
        super (filepath, fontSize);
        m_Filepath = filepath;
        m_BitmapWidth = width;
        m_BitmapHeight = height;
        m_Data = init();
    }
    
    private STBTTBakedChar.Buffer init()
    {
        int texID = glGenTextures();
        STBTTBakedChar.Buffer cdata = STBTTBakedChar.malloc(96);
        
        ByteBuffer ttf = load(m_Filepath, 160 * 1024);
        if (ttf == null)
        {
            return null;
        }
        
        ByteBuffer bitmap = BufferUtils.createByteBuffer(m_BitmapWidth * m_BitmapHeight);
        stbtt_BakeFontBitmap(ttf, getFontHeight(), bitmap, m_BitmapWidth, m_BitmapHeight, 32, cdata);
        
        glBindTexture(GL_TEXTURE_2D, texID);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_ALPHA, m_BitmapWidth, m_BitmapHeight, 0, GL_ALPHA, GL_UNSIGNED_BYTE, bitmap);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        
        return cdata;
    }
    
    public void drawString(String str, Vector2 pos)
    {
        float scaleFactor = 1.0f + getScale() * 0.25f;
        
        glPushMatrix();
        
        glScalef(scaleFactor, scaleFactor, 1f);
        
        glTranslatef(pos.getX(), getFontHeight() * 0.5f + pos.getY() - getLineOffset() * getFontHeight(), 0f);
    
        renderText(m_Data, m_BitmapWidth, m_BitmapHeight);
    
        glPopMatrix();
    }
    
    private void renderText(STBTTBakedChar.Buffer cdata, int width, int height) {
        try ( MemoryStack stack = stackPush() ) {
            FloatBuffer x = stack.floats(0.0f);
            FloatBuffer y = stack.floats(0.0f);
            
            STBTTAlignedQuad q = STBTTAlignedQuad.mallocStack(stack);
            
            glBegin(GL_QUADS);
            for ( int i = 0; i < this.getText().length(); i++ ) {
                char c = this.getText().charAt(i);
                if ( c == '\n' ) {
                    y.put(0, y.get(0) + getFontHeight());
                    x.put(0, 0.0f);
                    continue;
                } else if ( c < 32 || 128 <= c )
                    continue;
                stbtt_GetBakedQuad(cdata, width, height, c - 32, x, y, q, true);
                
                glTexCoord2f(q.s0(), q.t0());
                glVertex2f(q.x0(), q.y0());
                
                glTexCoord2f(q.s1(), q.t0());
                glVertex2f(q.x1(), q.y0());
                
                glTexCoord2f(q.s1(), q.t1());
                glVertex2f(q.x1(), q.y1());
                
                glTexCoord2f(q.s0(), q.t1());
                glVertex2f(q.x0(), q.y1());
            }
            glEnd();
        }
    }
}
