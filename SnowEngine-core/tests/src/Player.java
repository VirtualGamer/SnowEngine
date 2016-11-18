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
import com.snowengine.input.KeyCode;
import com.snowengine.input.Keyboard;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.GameObject;
import com.snowengine.objects.entities.AnimatedEntity;

public class Player extends AnimatedEntity
{
    private AudioListener m_Listener;
    public float speed;
    
    public Player()
    {
        super ("textures/player.png", 1, 7);
        m_Listener = new AudioListener();
        this.speed = 5;
    }
    
    @Override
    public void onCollision(GameObject other)
    {
        super.onCollision(other);
        
        if (other instanceof Crate)
        {
            Crate crate = (Crate) other;
            float xa = 0, ya = 0;
            Vector2 pos = this.getPosition(), opos = crate.getPosition();
            if (pos.getX() < opos.getX())
            {
                xa = -1;
            }
            else if (pos.getX() > opos.getX())
            {
                xa = +1;
            }
            if (pos.getY() < opos.getY())
            {
                ya = -1;
            }
            else if (pos.getY() > opos.getY())
            {
                ya = +1;
            }
            this.move(new Vector2(xa, ya));
            
            if (Keyboard.getKeyPressed(KeyCode.Space))
            {
                crate.destroy();
            }
        }
        
        if (other instanceof Coin)
        {
            Coin coin = (Coin) other;
            coin.destroy();
        }
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
        Vector2 horSpeed = new Vector2(this.speed, 0);
        Vector2 verSpeed = new Vector2(0, this.speed);
        
        if (Keyboard.getKey(KeyCode.Up))
        {
            this.move(verSpeed.negate());
        }
        if (Keyboard.getKey(KeyCode.Down))
        {
            this.move(verSpeed);
        }
    
        if (Keyboard.getKey(KeyCode.Left))
        {
            this.move(horSpeed.negate());
        }
        if (Keyboard.getKey(KeyCode.Right))
        {
            this.move(horSpeed);
        }
        
        super.update();
    }
}
