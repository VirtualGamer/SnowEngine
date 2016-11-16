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

import com.snowengine.maths.Matrix4;
import com.snowengine.maths.Vector3;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects</package>
 * <class>Transform</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public final class Transform extends Object
{
    public Vector3 position, rotation, scale;
    
    public Transform()
    {
        super ("Transform", 1);
        position = new Vector3();
        rotation = new Vector3();
        scale = new Vector3(1, 1, 1);
    }
    
    public Matrix4 getPositionMatrix()
    {
        return Matrix4.translate(this.position);
    }
    
    public Matrix4 getRotationMatrix()
    {
        Matrix4 rotationX = Matrix4.rotate(this.rotation.x, new Vector3(1, 0, 0));
        Matrix4 rotationY = Matrix4.rotate(this.rotation.y, new Vector3(0, 1, 0));
        Matrix4 rotationZ = Matrix4.rotate(this.rotation.z, new Vector3(0, 0, 1));
        return rotationZ.multiply(rotationY.multiply(rotationX));
    }
    
    public Matrix4 getScaleMatrix()
    {
        return Matrix4.scale(this.scale);
    }
    
    public Matrix4 getTransformMatrix()
    {
        return this.getPositionMatrix().multiply(this.getRotationMatrix().multiply(this.getScaleMatrix()));
    }
}
