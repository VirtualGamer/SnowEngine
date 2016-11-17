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

import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.sprites.AnimatedSprite;
import com.snowengine.objects.sprites.Sprite;

public final class BoxCollider extends Collider
{
    public BoxCollider()
    {
        super ("BoxCollider", 4);
    }
    
    @Override
    public boolean isColliding(Vector3 vector)
    {
        Vector2[] extents = null;
        if (this.parent instanceof Sprite)
        {
            extents = ((Sprite) this.parent).getExtents();
        }
        else if (this.parent instanceof AnimatedSprite)
        {
            extents = ((AnimatedSprite) this.parent).getExtents();
        }
        return (extents != null) && intersect(new Vector2(vector.x, vector.y), extents);
    }
    
    @Override
    public boolean isColliding(Collider other)
    {
        if (other instanceof BoxCollider)
        {
            BoxCollider collider = (BoxCollider) other;
            Vector2[] extents = null;
            if (this.parent instanceof Sprite)
            {
                extents = ((Sprite) this.parent).getExtents();
            }
            else if (this.parent instanceof AnimatedSprite)
            {
                extents = ((AnimatedSprite) this.parent).getExtents();
            }
            if (extents == null)
            {
                return false;
            }
            else
            {
                Vector2[] extents2 = null;
                if (collider.parent instanceof Sprite)
                {
                    extents2 = ((Sprite) collider.parent).getExtents();
                }
                else if (collider.parent instanceof AnimatedSprite)
                {
                    extents2 = ((AnimatedSprite) collider.parent).getExtents();
                }
                return ((extents2 != null) && (this.intersect(extents, extents2) || this.intersect(extents2, extents)));
            }
        }
        return false;
    }
    
    private boolean intersect(Vector2[] c, Vector2[] d)
    {
        float dx = c[1].x - c[0].x;
        float dy = c[1].y - c[0].y;
        float lengthSquared = (dy * dy + dx * dx);
    
        if (lengthSquared == 0.0f)
        {
            lengthSquared = 1.0f;
        }
    
        float t, minT, maxT;
    
        t = ((d[0].x - c[0].x) * dx + (d[0].y - c[0].y) * dy) / lengthSquared;
        maxT = t;
        minT = t;
    
        t = ((d[1].x - c[0].x) * dx + (d[1].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT, t);
        maxT = Math.max(maxT, t);
    
        t = ((d[2].x - c[0].x) * dx + (d[2].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT, t);
        maxT = Math.max(maxT, t);
    
        t = ((d[3].x - c[0].x) * dx + (d[3].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT, t);
        maxT = Math.max(maxT, t);
    
        if ((minT >= 1) || (maxT < 0))
        {
            return false;
        }
    
        dx = c[3].x - c[0].x;
        dy = c[3].y - c[0].y;
        lengthSquared = (dy * dy + dx * dx);
    
        if (lengthSquared == 0.0f)
        {
            lengthSquared = 1.0f;
        }
    
        t = ((d[0].x - c[0].x) * dx + (d[0].y - c[0].y) * dy) / lengthSquared;
        maxT = t;
        minT = t;
    
        t = ((d[1].x - c[0].x) * dx + (d[1].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT, t);
        maxT = Math.max(maxT, t);
    
        t = ((d[2].x - c[0].x) * dx + (d[2].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT, t);
        maxT = Math.max(maxT, t);
    
        t = ((d[3].x - c[0].x) * dx + (d[3].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT, t);
        maxT = Math.max(maxT, t);
    
        return !((minT >= 1) || (maxT < 0));
    }
    
    private boolean intersect(Vector2 p, Vector2[] c)
    {
        float dx = c[1].x - c[0].x;
        float dy = c[1].y - c[0].y;
        float lengthSquared = (dy * dy + dx * dx);
    
        float t;
    
        t = ((p.x - c[0].x) * dx + (p.y - c[0].y) * dy) / lengthSquared;
    
        if ((t > 1) || (t < 0))
        {
            return false;
        }
    
        dx = c[3].x - c[0].x;
        dy = c[3].y - c[0].y;
        lengthSquared = (dy * dy + dx * dx);
    
        t = ((p.x - c[0].x) * dx + (p.y - c[0].y) * dy) / lengthSquared;
    
        return !((t > 1) || (t < 0));
    }
}
