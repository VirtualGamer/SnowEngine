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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class TextData extends FileData
{
    private StringBuilder m_FileSource;

    protected TextData(String filepath)
    {
        super (filepath);
    }

    @Override
    protected void load()
    {
        try
        {
            m_FileSource = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(m_FilePath));
            String line;

            while ((line = reader.readLine()) != null)
            {
                m_FileSource.append(line).append("\n");
            }

            reader.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public String getText()
    {
        return m_FileSource.toString();
    }
}
