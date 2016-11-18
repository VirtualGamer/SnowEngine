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
import java.util.List;

public final class CollisionManager
{
    public CollisionManager()
    {
    }
    
    public void checkForCollisions(final List<GameObject> objects)
    {
        final List<GameObject> copyList = new ArrayList<>();
        copyList.addAll(objects);
        objects.forEach(object -> {
            GameObject[] collisions = getCollisions(object, copyList);
    
            for (GameObject collision : collisions)
            {
                if (object != collision)
                {
                    object.onCollision(collision);
                }
            }
        });
    }
    
    private GameObject[] getCollisions(final GameObject object, List<GameObject> others)
    {
        List<GameObject> result = new ArrayList<>();
        
        others.forEach(other -> {
            if (object.isColliding(other) || other.isColliding(object))
            {
                result.add(other);
            }
        });
        
        return result.toArray(new GameObject[result.size()]);
    }
}
