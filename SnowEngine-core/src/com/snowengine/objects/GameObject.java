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

import com.snowengine.graphics.Shader;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.colliders.Collider;

import java.util.ArrayList;
import java.util.List;

/**
 * <summary>
 * <project>SnowEngine</project>
 * <package>com.snowengine.objects</package>
 * <class>GameObject</class>
 * <since>1.0</since>
 * </summary>
 *
 * @author Mark Rienstra
 * @since 1.0
 */
public class GameObject extends Object
{
    public GameObject parent;
    public Transform transform;
    public Collider collider;
    
    private List<GameObject> m_Children;
    
    public GameObject(String name, int instanceID)
    {
        super (name, instanceID);
        this.transform = new Transform();
        this.transform.gameObject = this;
        m_Children = new ArrayList<>();
        collider = this.createCollider();
        if (collider != null)
        {
            collider.parent = this;
        }
    }
    
    protected Collider createCollider()
    {
        return null;
    }
    
    public void addChild(GameObject gameObject)
    {
        if (m_Children.contains(gameObject))
        {
            System.out.println(this + " already contains an instance of " + gameObject);
        }
        else
        {
            m_Children.add(gameObject);
            gameObject.parent = this;
        }
    }
    
    public void removeChild(GameObject gameObject)
    {
        if (!m_Children.contains(gameObject))
        {
            System.out.println(this + " does not contain an instance of " + gameObject);
        }
        else
        {
            m_Children.remove(gameObject);
            gameObject.parent = null;
        }
    }
    
    public void destroy()
    {
        if (this.parent != null)
        {
            this.parent.removeChild(this);
        }
        this.onDestroy();
    }
    
    public void onDestroy()
    {
    }
    
    public void onCollision(GameObject other)
    {
    }
    
    public void move(Vector2 vector)
    {
        this.move(new Vector3(vector.getX(), vector.getY(), 0));
    }
    
    public void move(Vector3 vector)
    {
        this.transform.move(vector);
    }
    
    public void rotate(Vector2 vector)
    {
        this.rotate(new Vector3(vector.x, 0, vector.y));
    }
    
    public void rotate(Vector3 vector)
    {
        this.transform.rotate(vector);
    }
    
    public void scale(Vector2 vector)
    {
        this.scale(new Vector3(vector.x, vector.y, 0.0f));
    }
    
    public void scale(Vector3 vector)
    {
        this.transform.scale(vector);
    }
    
    public void update()
    {
        m_Children.forEach(GameObject::update);
    }
    
    public void render()
    {
        Shader shader = Shader.getActiveShader();
        shader.setUniformMatrix4f("model", this.transform.getTransformMatrix());
        m_Children.forEach(GameObject::render);
    }
    
    public GameObject[] getChildren()
    {
        return m_Children.toArray(new GameObject[m_Children.size()]);
    }
    
    public boolean isColliding(GameObject other)
    {
        if (collider != null)
        {
            if (other.collider != null)
            {
                return collider.isColliding(other.collider);
            }
            return collider.isColliding(other.transform.getPosition());
        }
        else
        {
            if (other.collider != null)
            {
                return other.collider.isColliding(this.transform.getPosition());
            }
            return false;
        }
    }
}
