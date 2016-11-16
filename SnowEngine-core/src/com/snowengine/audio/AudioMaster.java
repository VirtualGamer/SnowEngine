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

import com.snowengine.utils.FileUtils;
import org.lwjgl.openal.ALCCapabilities;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.openal.AL.createCapabilities;
import static org.lwjgl.openal.AL10.alDistanceModel;
import static org.lwjgl.openal.AL11.alSpeedOfSound;
import static org.lwjgl.openal.ALC.createCapabilities;
import static org.lwjgl.openal.ALC10.*;

public final class AudioMaster
{
    private static long m_Device, m_Context;
    private static String m_Version;
    private static AMErrorCallback m_ErrorCallback;
    private static Map<String, AudioClip> m_AudioClips;

    public static boolean init()
    {
        m_Device = alcOpenDevice((ByteBuffer) null);
        m_Context = alcCreateContext(m_Device, (int[]) null);
        boolean successful = alcMakeContextCurrent(m_Context);
        ALCCapabilities capabilities = createCapabilities(m_Device);
        createCapabilities(capabilities);
        int major = alcGetInteger(m_Device, ALC_MAJOR_VERSION);
        int minor = alcGetInteger(m_Device, ALC_MINOR_VERSION);
        m_Version = major + "." + minor;
        m_AudioClips = new HashMap<>();
        return successful;
    }

    public static void terminate()
    {
        m_AudioClips.values().forEach(AudioClip::delete);
        alcDestroyContext(m_Context);
        alcCloseDevice(m_Device);
    }

    public static String getVersionString()
    {
        return m_Version;
    }

    public static void setErrorCallback(AMErrorCallback cbFun)
    {
        m_ErrorCallback = cbFun;
    }

    public static void setDistanceModel(DistanceModel model)
    {
        alDistanceModel(model.getPointer());
    }

    public static void setSpeedOfSound(float speed)
    {
        alSpeedOfSound(speed);
    }

    public static void poll()
    {
        if (m_ErrorCallback == null)
        {
            return;
        }
        int error = alcGetError(m_Device);
        if (error != ALC_NO_ERROR)
        {
            m_ErrorCallback.errorCallback(error, alcGetString(m_Device, error));
        }
    }

    public static AudioClip loadAudioClip(String filepath)
    {
        if (m_AudioClips.containsKey(filepath))
        {
            return m_AudioClips.get(filepath);
        }
        else
        {
            AudioClip clip = new AudioClip(FileUtils.openAudioFile(filepath));
            m_AudioClips.put(filepath, clip);
            return clip;
        }
    }
}
