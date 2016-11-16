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
package com.snowengine.maths;

public final class Vector2
{
    public float x, y;

    public Vector2()
    {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 other)
    {
        this.x += other.x;
        this.y += other.y;

        return this;
    }

    public Vector2 subtract(Vector2 other)
    {
        this.x -= other.x;
        this.y -= other.y;

        return this;
    }

    public Vector2 multiply(Vector2 other)
    {
        this.x *= other.x;
        this.y *= other.y;

        return this;
    }

    public Vector2 divide(Vector2 other)
    {
        this.x /= other.x;
        this.y /= other.y;

        return this;
    }

    public Vector2 add(float value)
    {
        this.x += value;
        this.y += value;

        return this;
    }

    public Vector2 subtract(float value)
    {
        this.x -= value;
        this.y -= value;

        return this;
    }

    public Vector2 multiply(float value)
    {
        this.x *= value;
        this.y *= value;

        return this;
    }

    public Vector2 divide(float value)
    {
        this.x /= value;
        this.y /= value;

        return this;
    }

    public Vector2 copy()
    {
        return new Vector2(this.x, this.y);
    }

    public Vector2 normalize()
    {
        return this.divide(this.length());
    }

    public Vector2 normalized()
    {
        return this.copy().normalize();
    }

    public Vector2 negate()
    {
        return new Vector2(-this.x, -this.y);
    }
    
    public Vector2 lerp(Vector2 destination, float lerpFactor)
    {
        return destination.subtract(this).multiply(lerpFactor).add(this);
    }

    public float lengthSquared()
    {
        return (this.x * this.x) + (this.y * this.y);
    }

    public float length()
    {
        return Mathf.sqrt(this.lengthSquared());
    }

    public float getX()
    {
        return this.x;
    }

    public float getY()
    {
        return this.y;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Vector2)
        {
            Vector2 vector = (Vector2) obj;
            return (this.x == vector.x) && (this.y == vector.y);
        }
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return "Vector2(" + this.x + ", " + this.y + ")";
    }
}
