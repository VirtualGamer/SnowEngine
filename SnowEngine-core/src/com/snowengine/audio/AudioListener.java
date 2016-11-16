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
package com.snowengine.audio;

import com.snowengine.maths.Vector3;

import static org.lwjgl.openal.AL10.*;

public final class AudioListener
{
    private static AudioListener m_ActiveListener = null;
    private float m_MinVolume, m_MaxVolume, m_Volume, m_Pitch;
    private Vector3 m_Position, m_Velocity;

    public AudioListener()
    {
        m_Volume = 1;
        m_MinVolume = 0;
        m_MaxVolume = 10;
        m_Pitch = 1;
        m_Position = new Vector3();
        m_Velocity = new Vector3();

        if (m_ActiveListener == null)
        {
            this.makeCurrent();
        }
    }

    public void makeCurrent()
    {
        m_ActiveListener = this;
        alListenerf(AL_GAIN, m_Volume);
        alListenerf(AL_MIN_GAIN, m_MinVolume);
        alListenerf(AL_MAX_GAIN, m_MaxVolume);
        alListenerf(AL_PITCH, m_Pitch);
        alListener3f(AL_POSITION, m_Position.getX(), m_Position.getY(), m_Position.getZ());
        alListener3f(AL_VELOCITY, m_Velocity.getX(), m_Velocity.getY(), m_Velocity.getZ());
    }

    public void setVolume(float min, float max)
    {
        m_MinVolume = min;
        m_MaxVolume = max;

        if (this.isActive())
        {
            alListenerf(AL_MIN_GAIN, m_MinVolume);
            alListenerf(AL_MAX_GAIN, m_MaxVolume);
        }
    }

    public void setVolume(float volume)
    {
        m_Volume = volume;

        if (this.isActive())
        {
            alListenerf(AL_GAIN, m_Volume);
        }
    }

    public void setPitch(float pitch)
    {
        m_Pitch = pitch;

        if (this.isActive())
        {
            alListenerf(AL_PITCH, m_Pitch);
        }
    }

    public void setPosition(Vector3 position)
    {
        m_Position = position;

        if (this.isActive())
        {
            alListener3f(AL_POSITION, m_Position.getX(), m_Position.getY(), m_Position.getZ());
        }
    }

    public void setVelocity(Vector3 velocity)
    {
        m_Velocity = velocity;


        if (this.isActive())
        {
            alListener3f(AL_VELOCITY, m_Velocity.getX(), m_Velocity.getY(), m_Velocity.getZ());
        }
    }

    public float getMinVolume()
    {
        return m_MinVolume;
    }

    public float getMaxVolume()
    {
        return m_MaxVolume;
    }

    public float getVolume()
    {
        return m_Volume;
    }

    public float getPitch()
    {
        return m_Pitch;
    }

    public Vector3 getPosition()
    {
        return m_Position;
    }

    public Vector3 getVelocity()
    {
        return m_Velocity;
    }

    public boolean isActive()
    {
        return (m_ActiveListener == this);
    }
}
