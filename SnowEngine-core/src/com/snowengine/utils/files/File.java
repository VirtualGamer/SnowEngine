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

public abstract class File
{
    private FileData m_FileData;

    protected File(String filepath, DataType type)
    {
        switch (type)
        {
            case Text:
                m_FileData = new TextData(filepath);
                break;
            case Audio:
                m_FileData = new WaveData(filepath);
                break;
            case Image:
                m_FileData = new ImageData(filepath);
                break;
            case XML:
                m_FileData = new XMLData(filepath);
                break;
            case TMX:
            default:
                throw new IllegalArgumentException("Unknown file data type (" + type + ")");
        }
    }

    public String getFilePath()
    {
        return m_FileData.getFilePath();
    }

    public FileData getFileData()
    {
        return m_FileData;
    }
}
