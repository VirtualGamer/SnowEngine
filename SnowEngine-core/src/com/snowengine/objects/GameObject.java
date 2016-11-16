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

import java.util.ArrayList;
import java.util.List;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects</package>
 * <class>GameObject</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public class GameObject extends Object
{
    private List<GameObject> m_Children;
    
    public GameObject(String name, int instanceID)
    {
        super (name, instanceID);
        m_Children = new ArrayList<>();
    }
    
    public void addChild(GameObject gameObject)
    {
        if (m_Children.contains(gameObject))
        {
            System.out.println(this + " already contains an instance of " + gameObject);
        }
        else
        {
            m_Children.add(gameObject);
        }
    }
    
    public void removeChild(GameObject gameObject)
    {
        if (!m_Children.contains(gameObject))
        {
            System.out.println(this + " does not contain an instance of " + gameObject);
        }
        else
        {
            m_Children.remove(gameObject);
        }
    }
    
    public GameObject[] getChildren()
    {
        return m_Children.toArray(new GameObject[m_Children.size()]);
    }
}
