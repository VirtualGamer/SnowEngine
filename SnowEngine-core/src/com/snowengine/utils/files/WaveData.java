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

import org.lwjgl.BufferUtils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.openal.AL10.*;

public final class WaveData extends FileData
{
    private int m_Format, m_SampleRate, m_TotalBytes, m_BytesPerFrame;
    private ByteBuffer m_Data;
    private AudioInputStream m_AudioStream;
    private byte[] m_DataArray;

    protected WaveData(String filepath)
    {
        super (filepath);
    }

    public void delete()
    {
        try
        {
            m_AudioStream.close();
            m_Data.clear();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void load()
    {
        m_AudioStream = GetStream(m_FilePath);
        if (m_AudioStream == null)
        {
            throw new NullPointerException("Cannot open a valid audio stream!");
        }
        AudioFormat audioFormat = m_AudioStream.getFormat();
        m_Format = GetOpenAlFormat(audioFormat.getChannels(), audioFormat.getSampleSizeInBits());
        m_SampleRate = (int) audioFormat.getSampleRate();
        m_BytesPerFrame = audioFormat.getFrameSize();
        m_TotalBytes = (int) (m_AudioStream.getFrameLength() * m_BytesPerFrame);
        m_Data = BufferUtils.createByteBuffer(m_TotalBytes);
        m_DataArray = new byte[m_TotalBytes];
        this.loadData();
    }

    private ByteBuffer loadData()
    {
        try
        {
            int bytesRead = m_AudioStream.read(m_DataArray, 0, m_TotalBytes);
            m_Data.clear();
            m_Data.put(m_DataArray, 0, bytesRead);
            m_Data.flip();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Couldn't read bytes from audio stream!");
        }
        return m_Data;
    }

    public int getFormat()
    {
        return m_Format;
    }

    public int getSampleRate()
    {
        return m_SampleRate;
    }

    public int getTotalBytes()
    {
        return m_TotalBytes;
    }

    public int getBytesPerFrame()
    {
        return m_BytesPerFrame;
    }

    public ByteBuffer getData()
    {
        return m_Data;
    }

    private static AudioInputStream GetStream(String filepath)
    {
        AudioInputStream audioStream = null;
        try
        {
            InputStream stream = new FileInputStream(filepath);
            InputStream bufferedInput = new BufferedInputStream(stream);
            audioStream = AudioSystem.getAudioInputStream(bufferedInput);
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            e.printStackTrace();
        }
        return audioStream;
    }

    private static int GetOpenAlFormat(int channels, int bitsPerSample)
    {
        if (channels == 1)
        {
            return bitsPerSample == 8 ? AL_FORMAT_MONO8 : AL_FORMAT_MONO16;
        }
        else
        {
            return bitsPerSample == 8 ? AL_FORMAT_STEREO8 : AL_FORMAT_STEREO16;
        }
    }
}
