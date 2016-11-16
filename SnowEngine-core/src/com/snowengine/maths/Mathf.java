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

public final class Mathf
{
    public static final float E  = 2.7182818f;
    public static final float PI = 3.1415927f;

    // Hidden constructor
    private Mathf()
    {
    }

    public static float toRadians(float angleInDegrees)
    {
        return (angleInDegrees * PI) / 180.0f;
    }

    public static float toDegrees(float angleInRadians)
    {
        return (angleInRadians / PI) * 180.0f;
    }

    public static float sqrt(float a)
    {
        return (float) Math.sqrt(a);
    }

    public static float sin(float a)
    {
        return (float) Math.sin(a);
    }

    public static float cos(float a)
    {
        return (float) Math.cos(a);
    }

    public static float tan(float a)
    {
        return (float) Math.tan(a);
    }
}
