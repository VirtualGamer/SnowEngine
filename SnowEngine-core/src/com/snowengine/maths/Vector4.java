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

public final class Vector4
{
    public float x, y, z, w;

    public Vector4()
    {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public Vector4(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4 add(Vector4 other)
    {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        this.w += other.w;

        return this;
    }

    public Vector4 subtract(Vector4 other)
    {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        this.w -= other.w;

        return this;
    }

    public Vector4 multiply(Vector4 other)
    {
        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.z;
        this.w *= other.w;

        return this;
    }

    public Vector4 divide(Vector4 other)
    {
        this.x /= other.x;
        this.y /= other.y;
        this.z /= other.z;
        this.w /= other.w;

        return this;
    }

    public Vector4 add(float value)
    {
        this.x += value;
        this.y += value;
        this.z += value;
        this.w += value;

        return this;
    }

    public Vector4 subtract(float value)
    {
        this.x -= value;
        this.y -= value;
        this.z -= value;
        this.w -= value;

        return this;
    }

    public Vector4 multiply(float value)
    {
        this.x *= value;
        this.y *= value;
        this.z *= value;
        this.w *= value;

        return this;
    }

    public Vector4 divide(float value)
    {
        this.x /= value;
        this.y /= value;
        this.z /= value;
        this.w /= value;

        return this;
    }

    public Vector4 copy()
    {
        return new Vector4(this.x, this.y, this.z, this.w);
    }

    public Vector4 normalize()
    {
        return this.divide(this.length());
    }

    public Vector4 normalized()
    {
        return this.copy().normalize();
    }

    public Vector4 negate()
    {
        return new Vector4(-this.x, -this.y, -this.z, -this.w);
    }

    public float lengthSquared()
    {
        return (this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w);
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

    public float getZ()
    {
        return this.z;
    }

    public float getW()
    {
        return this.w;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Vector4)
        {
            Vector4 vector = (Vector4) obj;
            return (this.x == vector.x) && (this.y == vector.y) && (this.z == vector.z) && (this.w == vector.w);
        }
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return "Vector3(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }
}
