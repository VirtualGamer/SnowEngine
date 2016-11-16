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

public final class AudioSource
{
    private int m_SourceID;
    private float m_MinVolume, m_MaxVolume, m_Volume, m_Pitch, m_RollOffFactor, m_ReferenceDistance, m_MaxDistance;
    private Vector3 m_Position, m_Velocity;
    private boolean m_Looping;

    public AudioSource()
    {
        m_SourceID = alGenSources();
        m_Position = new Vector3();
        m_Velocity = new Vector3();

        alSourcef(m_SourceID, AL_GAIN, m_Volume = 1);
        alSourcef(m_SourceID, AL_MIN_GAIN, m_MinVolume = 0);
        alSourcef(m_SourceID, AL_MAX_GAIN, m_MaxVolume = 10);
        alSourcef(m_SourceID, AL_PITCH, m_Pitch = 1);
        alSourcef(m_SourceID, AL_ROLLOFF_FACTOR, m_RollOffFactor = 1);
        alSourcef(m_SourceID, AL_REFERENCE_DISTANCE, m_ReferenceDistance = 100);
        alSourcef(m_SourceID, AL_MAX_DISTANCE, m_MaxDistance = 150);
        alSource3f(m_SourceID, AL_POSITION, m_Position.getX(), m_Position.getY(), m_Position.getZ());
        alSource3f(m_SourceID, AL_VELOCITY, m_Velocity.getX(), m_Velocity.getY(), m_Velocity.getZ());
    }

    public void delete()
    {
        alDeleteSources(m_SourceID);
    }

    public void setVolume(float min, float max)
    {
        alSourcef(m_SourceID, AL_MIN_GAIN, m_MinVolume = min);
        alSourcef(m_SourceID, AL_MAX_GAIN, m_MaxVolume = max);
    }

    public void setVolume(float volume)
    {
        alSourcef(m_SourceID, AL_GAIN, m_Volume = volume);
    }

    public void setPitch(float pitch)
    {
        alSourcef(m_SourceID, AL_PITCH, m_Pitch = pitch);
    }

    public void setRollOffFactor(float rollOffFactor)
    {
        alSourcef(m_SourceID, AL_ROLLOFF_FACTOR, m_RollOffFactor = rollOffFactor);
    }

    public void setReferenceDistance(float referenceDistance)
    {
        alSourcef(m_SourceID, AL_REFERENCE_DISTANCE, m_ReferenceDistance = referenceDistance);
    }

    public void setMaxDistance(float maxDistance)
    {
        alSourcef(m_SourceID, AL_MAX_DISTANCE, m_MaxDistance = maxDistance);
    }

    public void setPosition(Vector3 position)
    {
        m_Position = position;
        alSource3f(m_SourceID, AL_POSITION, m_Position.getX(), m_Position.getY(), m_Position.getZ());
    }

    public void setVelocity(Vector3 velocity)
    {
        m_Velocity = velocity;
        alListener3f(AL_VELOCITY, m_Velocity.getX(), m_Velocity.getY(), m_Velocity.getZ());
    }

    public void play(AudioClip clip)
    {
        alSourcei(m_SourceID, AL_BUFFER, clip.getBufferID());
        alSourcePlay(m_SourceID);
    }

    public void loop(AudioClip clip)
    {
        alSourcei(m_SourceID, AL_LOOPING, AL_TRUE);
        this.play(clip);
        m_Looping = true;
    }

    public void pause()
    {
        alSourcePause(m_SourceID);
    }

    public void rewind()
    {
        alSourceRewind(m_SourceID);
    }

    public void stop()
    {
        alSourceStop(m_SourceID);
        if (m_Looping)
        {
            alSourcei(m_SourceID, AL_LOOPING, AL_FALSE);
        }
        alSourcei(m_SourceID, AL_BUFFER, 0);
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

    public float getRollOffFactor()
    {
        return m_RollOffFactor;
    }

    public float getReferenceDistance()
    {
        return m_ReferenceDistance;
    }

    public float getMaxDistance()
    {
        return m_MaxDistance;
    }

    public Vector3 getPosition()
    {
        return m_Position;
    }

    public Vector3 getVelocity()
    {
        return m_Velocity;
    }

    public boolean isPlaying()
    {
        return alGetSourcei(m_SourceID, AL_SOURCE_STATE) == AL_PLAYING;
    }

    public boolean isLooping()
    {
        return m_Looping;
    }

    public boolean isPaused()
    {
        return alGetSourcei(m_SourceID, AL_SOURCE_STATE) == AL_PAUSED;
    }

    public boolean isStopped()
    {
        return alGetSourcei(m_SourceID, AL_SOURCE_STATE) == AL_STOPPED;
    }
}
