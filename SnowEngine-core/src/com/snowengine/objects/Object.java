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
package com.snowengine.objects;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects</package>
 * <class>Object</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public abstract class Object
{
    public String name;
    private final int m_InstanceID;
    
    public Object(String name, int instanceID)
    {
        this.name = name;
        m_InstanceID = instanceID;
    }
    
    public boolean equals(Object obj)
    {
        return (obj.m_InstanceID == m_InstanceID);
    }
    
    @Override
    public final boolean equals(java.lang.Object obj)
    {
        return super.equals(obj);
    }
    
    public int getInstanceID()
    {
        return m_InstanceID;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
