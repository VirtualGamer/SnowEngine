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

import com.snowengine.maths.Vector2;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects</package>
 * <class>Rectangle</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public final class Rectangle
{
    private Vector2 m_Position, m_Size;
    
    public Rectangle()
    {
        this (0, 0, 0, 0);
    }
    
    public Rectangle(float x, float y, float width, float height)
    {
        this.setBounds(x, y, width, height);
    }
    
    public void setBounds(float x, float y, float width, float height)
    {
        m_Position = new Vector2(x, y);
        m_Size = new Vector2(width, height);
    }
    
    public void move(Vector2 other)
    {
        m_Position.add(other);
    }
    
    public boolean isColliding(Vector2 other)
    {
        if (other.getX() < m_Position.getX() && other.getX() > (m_Position.getX() + m_Size.getX()))
        {
            if (other.getY() < m_Position.getY() && other.getY() > (m_Position.getY() + m_Size.getY()))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isColliding(Rectangle other)
    {
        Vector2[] c = this.getExtents();
        Vector2[] d = other.getExtents();
        return !areaOverlap(c, d) && areaOverlap(d, c);
    }
    
    private Vector2[] getExtents()
    {
        Vector2[] ret = new Vector2[4];
        ret[0] = new Vector2(m_Position.x, m_Position.y);
        ret[1] = new Vector2(m_Size.x, m_Position.y);
        ret[2] = new Vector2(m_Size.x, m_Size.y);
        ret[3] = new Vector2(m_Position.x, m_Size.y);
        return ret;
    }
    
    private boolean areaOverlap(Vector2 c[], Vector2 d[])
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
        maxT = t; minT = t;
    
        t = ((d[1].x - c[0].x) * dx + (d[1].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT,t); maxT = Math.max(maxT,t);
    
        t = ((d[2].x - c[0].x) * dx + (d[2].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT,t); maxT = Math.max(maxT,t);
    
        t = ((d[3].x - c[0].x) * dx + (d[3].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT,t); maxT = Math.max(maxT,t);
    
        if ((minT >= 1) || (maxT < 0))
        {
            return false;
        }
    
        dx = c[3].x - c[0].x;
        dy = c[3].y - c[0].y;
        lengthSquared = (dy * dy + dx * dx);
    
        if (lengthSquared == 0.0f) lengthSquared = 1.0f;
    
        t = ((d[0].x - c[0].x) * dx + (d[0].y - c[0].y) * dy) / lengthSquared;
        maxT = t; minT = t;
    
        t = ((d[1].x - c[0].x) * dx + (d[1].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT,t); maxT = Math.max(maxT,t);
    
        t = ((d[2].x - c[0].x) * dx + (d[2].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT,t); maxT = Math.max(maxT,t);
    
        t = ((d[3].x - c[0].x) * dx + (d[3].y - c[0].y) * dy) / lengthSquared;
        minT = Math.min(minT,t); maxT = Math.max(maxT,t);
    
        if ((minT >= 1) || (maxT < 0)) return false;
    
        return true;
    }
}
