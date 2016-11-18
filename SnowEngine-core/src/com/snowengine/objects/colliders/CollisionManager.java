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
package com.snowengine.objects.colliders;

import com.snowengine.objects.GameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CollisionManager
{
    public CollisionManager()
    {
    }
    
    public <T extends GameObject> void checkForCollisions(List<T> objects)
    {
        List<T> copyList = new ArrayList<>();
        copyList.addAll(objects);
        for (T object : objects)
        {
            GameObject[] collisions = getCollisions(object, copyList);
            
            for (GameObject collision : collisions)
            {
                object.onCollision(collision);
            }
        }
    }
    
    private <T extends GameObject> GameObject[] getCollisions(T object, List<T> others)
    {
        List<GameObject> result = new ArrayList<>();
        
        for (T other : others)
        {
            if (other == object)
            {
                continue;
            }
            
            if (object.isColliding(other))
            {
                result.add(other);
            }
        }
        
        return result.toArray(new GameObject[result.size()]);
    }
}
