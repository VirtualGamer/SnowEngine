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

public final class Vector3
{
    public float x, y, z;

    public Vector3()
    {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 other)
    {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;

        return this;
    }

    public Vector3 subtract(Vector3 other)
    {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;

        return this;
    }

    public Vector3 multiply(Vector3 other)
    {
        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.z;

        return this;
    }

    public Vector3 divide(Vector3 other)
    {
        this.x /= other.x;
        this.y /= other.y;
        this.z /= other.z;

        return this;
    }

    public Vector3 add(float value)
    {
        this.x += value;
        this.y += value;
        this.z += value;

        return this;
    }

    public Vector3 subtract(float value)
    {
        this.x -= value;
        this.y -= value;
        this.z -= value;

        return this;
    }

    public Vector3 multiply(float value)
    {
        this.x *= value;
        this.y *= value;
        this.z *= value;

        return this;
    }

    public Vector3 divide(float value)
    {
        this.x /= value;
        this.y /= value;
        this.z /= value;

        return this;
    }

    public Vector3 copy()
    {
        return new Vector3(this.x, this.y, this.z);
    }

    public Vector3 normalize()
    {
        return this.divide(this.length());
    }

    public Vector3 normalized()
    {
        return this.copy().normalize();
    }

    public Vector3 negate()
    {
        return new Vector3(-this.x, -this.y, -this.z);
    }

    public float lengthSquared()
    {
        return (this.x * this.x) + (this.y * this.y) + (this.z * this.z);
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

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Vector3)
        {
            Vector3 vector = (Vector3) obj;
            return (this.x == vector.x) && (this.y == vector.y) && (this.z == vector.z);
        }
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return "Vector3(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
