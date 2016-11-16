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
    public GameObject gameObject;
    private Vector3 m_Position, m_Rotation, m_Scale;
    
    public Transform()
    {
        super ("Transform", 1);
        m_Position = new Vector3();
        m_Rotation = new Vector3();
        m_Scale = new Vector3(1, 1, 1);
    }
    
    public void move(float x, float y, float z)
    {
        m_Position.add(new Vector3(x, y, z));
    }
    
    public void move(Vector3 vector)
    {
        m_Position.add(vector);
    }
    
    public Vector3 getPosition()
    {
        Vector3 result = m_Position.copy();
        if (this.gameObject.parent != null)
        {
            result.add(this.gameObject.parent.transform.getPosition());
        }
        return result;
    }
    
    public Vector3 getRotation()
    {
        Vector3 result = m_Rotation.copy();
        if (this.gameObject.parent != null)
        {
            result.add(this.gameObject.parent.transform.getRotation());
        }
        return result;
    }
    
    public Vector3 getScale()
    {
        return m_Scale;
    }
    
    public Matrix4 getPositionMatrix()
    {
        return Matrix4.translate(this.getPosition());
    }
    
    public Matrix4 getRotationMatrix()
    {
        Matrix4 rotationX = Matrix4.rotate(this.getRotation().x, new Vector3(1, 0, 0));
        Matrix4 rotationY = Matrix4.rotate(this.getRotation().y, new Vector3(0, 1, 0));
        Matrix4 rotationZ = Matrix4.rotate(this.getRotation().z, new Vector3(0, 0, 1));
        return rotationZ.multiply(rotationY.multiply(rotationX));
    }
    
    public Matrix4 getScaleMatrix()
    {
        return Matrix4.scale(this.getScale());
    }
    
    public Matrix4 getTransformMatrix()
    {
        return this.getPositionMatrix().multiply(this.getRotationMatrix().multiply(this.getScaleMatrix()));
    }
}
