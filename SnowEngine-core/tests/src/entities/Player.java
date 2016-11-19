package entities; /**
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
    private int m_Score, m_Timer, m_MaxTime, m_FrameIndex;
    
    public Player()
    {
        super ("textures/player.png", 1, 7);
        super.setBounds(0, 32, 16, 16);
        
        m_Listener = new AudioListener();
        this.speed = 5;
        m_Score = 0;
        m_Timer = 0;
        m_MaxTime = 10;
        m_FrameIndex = 0;
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
    
    @Override
    public void onCollision(GameObject other)
    {
        super.onCollision(other);
        boolean attack = Keyboard.getKeyPressed(KeyCode.Space);
        
        if (other instanceof Slime)
        {
            Slime slime = (Slime) other;
    
            if (attack)
            {
                slime.hit(this);
            }
        }
        else if (other instanceof Crate)
        {
            Crate crate = (Crate) other;
            float xa = 0, ya = 0, speed = 1;
            Vector2 pos = this.getPosition(), opos = crate.getPosition();
            
            if (pos.getY() > (opos.getY() - 1))
            {
                ya = speed;
            }
            else if (pos.getY() < (opos.getY() + 1))
            {
                ya = -speed;
            }
            if (pos.getX() < (opos.getX() - 1))
            {
                xa = speed;
            }
            else if (pos.getX() > (opos.getX() + 1))
            {
                xa = -speed;
            }
            
            this.move(new Vector2(xa, ya));
            
            if (attack)
            {
                crate.destroy();
            }
        }
    }
    
    public void setScore(int score)
    {
        m_Score = score;
    }
    
    public void addScore(int score)
    {
        m_Score += score;
    }
    
    public void removeScore(int score)
    {
        if (m_Score > score)
        {
            m_Score += score;
        }
        else
        {
            this.setScore(0);
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
    
    @Override
    public void render()
    {
        if (m_Timer >= m_MaxTime)
        {
            m_Timer = 0;
            this.setFrame((m_FrameIndex < 6) ? m_FrameIndex++ : (m_FrameIndex = 0));
        }
    
        m_Timer++;
        
        super.render();
    }
}
