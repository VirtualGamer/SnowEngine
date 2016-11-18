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

import com.snowengine.audio.AudioListener;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.GameObject;
import com.snowengine.objects.Level;
import com.snowengine.objects.entities.AnimatedEntity;
import com.snowengine.objects.entities.EntityBase;

public class Player extends AnimatedEntity
{
    private AudioListener m_Listener;
    
    public Player()
    {
        super ("textures/player.png", 1, 7);
        m_Listener = new AudioListener();
    }
    
    @Override
    public void onCollision(GameObject other)
    {
        super.onCollision(other);
    }
    
    @Override
    public void move(Vector3 vector)
    {
        super.move(vector);
        m_Listener.setPosition(this.transform.getPosition());
    }
    
    @Override
    public void update()
    {
        super.update();
        System.out.println("Update");
    }
}
