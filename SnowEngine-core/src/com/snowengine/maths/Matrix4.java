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

public final class Matrix4
{
    public final float elements[];

    private Matrix4()
    {
        this.elements = new float[16];
    }

    private Matrix4(float diagonal)
    {
        this.elements = new float[16];
        this.elements[0 + 0 * 4] = diagonal;
        this.elements[1 + 1 * 4] = diagonal;
        this.elements[2 + 2 * 4] = diagonal;
        this.elements[3 + 3 * 4] = diagonal;
    }

    public Matrix4 multiply(Matrix4 other)
    {
        float data[] = new float[16];

        for (int row = 0; row < 4; row++)
        {
            for (int col = 0; col < 4; col++)
            {
                float sum = 0.0f;
                for (int e = 0; e < 4; e++)
                {
                    sum += this.elements[col + e * 4] * other.elements[e + row * 4];
                }
                data[col + row * 4] = sum;
            }
        }

        System.arraycopy(data, 0, this.elements, 0, 16);
        return this;
    }

    public Matrix4 copy()
    {
        Matrix4 result = new Matrix4();
        System.arraycopy(this.elements, 0, result.elements, 0, 16);
        return result;
    }

    public static Matrix4 identity()
    {
        return new Matrix4(1.0f);
    }

    public static Matrix4 orthographic(float left, float right, float bottom, float top, float near, float far)
    {
        Matrix4 result = new Matrix4(1.0f);
        float width = right - left;
        float height = top - bottom;
        float depth = far - near;

        result.elements[0 + 0 * 4] = 2.0f / width;
        result.elements[1 + 1 * 4] = 2.0f / height;
        result.elements[2 + 2 * 4] =-2.0f / depth;

        result.elements[0 + 3 * 4] = (-right - left) / width;
        result.elements[1 + 3 * 4] = (-top - bottom) / height;
        result.elements[2 + 3 * 4] = (-far - near) / depth;

        return result;
    }

    public static Matrix4 perspective(float fov, float aspectRatio, float near, float far)
    {
        Matrix4 result = new Matrix4(1.0f);
        float tanHalfFov = 1.0f / Mathf.tan(Mathf.toRadians(0.5f * fov));
        float range = near - far;

        result.elements[0 + 0 * 4] = tanHalfFov / aspectRatio;
        result.elements[1 + 1 * 4] = tanHalfFov;
        result.elements[2 + 2 * 4] = (-near - far) / range;
        result.elements[3 + 2 * 4] = -1.0f;
        result.elements[2 + 3 * 4] = (2.0f * near * far) / range;

        return result;
    }

    public static Matrix4 translate(Vector3 position)
    {
        Matrix4 result = new Matrix4(1.0f);

        result.elements[0 + 3 * 4] = position.x;
        result.elements[1 + 3 * 4] = position.y;
        result.elements[2 + 3 * 4] = position.z;

        return result;
    }

    public static Matrix4 rotate(float angle, Vector3 axis)
    {
        Matrix4 result = new Matrix4(1.0f);

        float rad = Mathf.toRadians(angle);
        float sin = Mathf.sin(rad);
        float cos = Mathf.cos(rad);
        float omc = 1.0f - cos;

        float x = axis.x;
        float y = axis.y;
        float z = axis.z;

        result.elements[0 + 0 * 4] = x * omc + cos;
        result.elements[1 + 0 * 4] = y * x * omc + z * sin;
        result.elements[2 + 0 * 4] = x * z * omc - y * sin;

        result.elements[0 + 1 * 4] = x * y * omc - z * sin;
        result.elements[1 + 1 * 4] = y * omc + cos;
        result.elements[2 + 1 * 4] = y * z * omc + x * sin;

        result.elements[0 + 2 * 4] = x * z * omc + y * sin;
        result.elements[1 + 2 * 4] = y * z * omc - x * sin;
        result.elements[2 + 2 * 4] = z * omc + cos;

        return result;
    }

    public static Matrix4 scale(Vector3 scale)
    {
        Matrix4 result = new Matrix4(1.0f);

        result.elements[0 + 0 * 4] = scale.x;
        result.elements[1 + 1 * 4] = scale.y;
        result.elements[2 + 2 * 4] = scale.z;

        return result;
    }
}
