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
package com.snowengine.utils;

import com.snowengine.maths.Vector3;

public final class ColorUtils
{
    private ColorUtils()
    {
    }
    
    public static Vector3 translate(int hexColor)
    {
        float r = ((0xff0000 & hexColor) >> 16) / 255.0f;
        float g = ((0xff00 & hexColor) >> 8) / 255.0f;
        float b = ((0xff & hexColor)) / 255.0f;
        return new Vector3(r, g, b);
    }
    
    public static int translate(Vector3 color)
    {
        int r = 0xff0000 & (int) (color.getX() * 255.0f);
        int g = 0xff00 & (int) (color.getY() * 255.0f);
        int b = 0xff & (int) (color.getZ() * 255.0f);
        return 255 | r << 24 | g << 8 | b;
    }
}
