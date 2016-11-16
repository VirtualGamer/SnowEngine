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
package com.snowengine.utils;

import com.snowengine.utils.files.*;

import java.util.HashMap;
import java.util.Map;

public final class FileUtils
{
    private static Map<String, File> m_Files;
    private static String m_PathPrefix = "./";

    static
    {
        m_Files = new HashMap<>();
    }

    // Hidden Constructor
    private FileUtils()
    {
    }

    public static void setPathPrefix(String prefix)
    {
        m_PathPrefix = prefix;
    }

    public static String getPathPrefix()
    {
        return m_PathPrefix;
    }

    public static File openFile(String filepath, DataType type)
    {
        filepath = (m_PathPrefix + filepath);
        if (m_Files.containsKey(filepath))
        {
            return m_Files.get(filepath);
        }
        else
        {
            File file;
            switch (type)
            {
                case Text:
                    file = new TextFile(filepath);
                    break;
                case Audio:
                    file = new AudioFile(filepath);
                    break;
                case Image:
                    file = new ImageFile(filepath);
                    break;
                default:
                    return null;
            }
            m_Files.put(filepath, file);
            return file;
        }
    }

    public static TextFile openTextFile(String filepath)
    {
        return (TextFile) openFile(filepath, DataType.Text);
    }

    public static AudioFile openAudioFile(String filepath)
    {
        return (AudioFile) openFile(filepath, DataType.Audio);
    }

    public static ImageFile openImageFile(String filepath)
    {
        return (ImageFile) openFile(filepath, DataType.Image);
    }

    public static void closeFiles()
    {
        m_Files.values().forEach(file -> {
            if (file instanceof AudioFile)
            {
                ((AudioFile) file).delete();
            }
        });
    }
}
