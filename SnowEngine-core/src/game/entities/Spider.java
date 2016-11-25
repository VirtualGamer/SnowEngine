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
package game.entities;

import com.snowengine.audio.AudioClip;
import com.snowengine.audio.AudioMaster;
import com.snowengine.audio.AudioSource;
import com.snowengine.maths.Vector2;
import com.snowengine.objects.GameObject;
import com.snowengine.objects.entities.AnimatedEntity;
import com.snowengine.objects.entities.EntityBase;

import java.util.Random;

public final class Spider extends AnimatedEntity
{
    private static AudioClip m_AudioClip;
    private AudioSource m_AudioSource;
    private int m_Health, m_Timer, m_MaxTime, m_FrameIndex, m_FramePointer;
    private int m_DirectionTimer, m_MaxDirectionTime;
    private boolean m_Moving, m_Hit, m_Attacking, m_Alive, m_DeadAnimation;
    private float xa = 0, ya = 0;
    
    public Spider()
    {
        super ("textures/spider.png", 4, 8);
    
        if (m_AudioClip == null)
        {
            m_AudioClip = AudioMaster.loadAudioClip("audio/enemy_hit.wav");
        }
    
        m_AudioSource = new AudioSource();
        m_Health = 5 + new Random().nextInt(3);
        m_Timer = 0;
        m_MaxTime = 4;
        m_FrameIndex = 0;
        m_FramePointer = 8;
        m_DirectionTimer = 0;
        m_MaxDirectionTime = 60;
        m_Moving = false;
        m_Hit = false;
        m_Attacking = false;
        m_Alive = true;
        m_DeadAnimation = false;
    }
    
    public void die()
    {
        this.setFrame(4);
        m_Alive = false;
        m_DeadAnimation = true;
    }
    
    @Override
    public void onCollision(GameObject other)
    {
        if (other instanceof Player)
        {
            if (!m_Attacking && !m_Hit && m_Alive)
            {
                Random random = new Random();
                int chance = random.nextInt(11);
                
                if (chance == 0)
                {
                    m_Attacking = true;
                    if (m_FramePointer < 0)
                    {
                        m_FramePointer = -m_FramePointer;
                    }
                    this.setFrame(m_FrameIndex = 1);
                }
            }
        }
    }
    
    public void hit(EntityBase attacker)
    {
        if (m_Alive)
        {
            Vector2 opos = attacker.getPosition();
            if (attacker instanceof Player)
            {
                Player player = (Player) attacker;
                player.addScore(1);
            }
    
            m_Hit = true;
            this.setFrame(m_FrameIndex = 3);
    
            if (opos.getX() < this.getPosition().getX())
            {
                xa = 1;
            }
            else if (opos.getX() > this.getPosition().getX())
            {
                xa = -1;
            }
            else
            {
                xa = 0;
            }
    
            if (opos.getY() < this.getPosition().getY())
            {
                ya = 1;
            }
            else if (opos.getY() > this.getPosition().getY())
            {
                ya = -1;
            }
            else
            {
                ya = 0;
            }
    
            m_AudioSource.setPosition(this.transform.getPosition());
            m_AudioSource.play(m_AudioClip);
            m_Health--;
        }
    }
    
    @Override
    public void update()
    {
        super.update();
    
        if (m_DirectionTimer >= m_MaxDirectionTime)
        {
            m_DirectionTimer = 0;
            m_Moving = false;
            
            if (m_Alive && !m_Hit && !m_Attacking)
            {
                int rand1 = new Random().nextInt(4);
                int rand2 = new Random().nextInt(4);
    
                if (rand1 == 0)
                {
                    xa = 1;
                    if (this.getScale().x > 0)
                    {
                        this.scale(new Vector2(-2, 0));
                    }
                }
                else if (rand1 == 1)
                {
                    xa = -1;
                    if (this.getScale().x < 0)
                    {
                        this.scale(new Vector2(2, 0));
                    }
                }
                else
                {
                    xa = 0;
                }
    
                if (rand2 == 0)
                {
                    ya = 1;
                }
                else if (rand2 == 1)
                {
                    ya = -1;
                }
                else
                {
                    ya = 0;
                }
            }
        }
    
        if (xa != 0 && ya != 0)
        {
            if (!m_Hit && !m_Attacking)
            {
                m_Moving = true;
            }
            this.move(new Vector2(xa, ya));
        }
    
        if ((m_Timer >= m_MaxTime))
        {
            m_Timer = 0;
            if (m_Alive)
            {
                if (m_Moving && m_Alive)
                {
                    int newFrame = m_FrameIndex + m_FramePointer;
                    if ((newFrame <= 0 || newFrame >= (4 * m_FramePointer)))
                    {
                        m_FramePointer = -m_FramePointer;
                    }
                    if (m_Moving)
                    {
                        this.setFrame(m_FrameIndex += m_FramePointer);
                    }
                    else
                    {
                        this.setFrame(0);
                    }
                }
                else if (m_Attacking)
                {
                    int newFrame = m_FrameIndex + m_FramePointer;
                    if (newFrame >= (2 * 4) + (m_FramePointer * 4))
                    {
                        m_Attacking = false;
                        this.setFrame(0);
                    }
                    else
                    {
                        this.setFrame(m_FrameIndex += m_FramePointer);
                    }
                }
                else if (m_Hit)
                {
                    int newFrame = m_FrameIndex + m_FramePointer;
                    if (newFrame >= (3 * 4) + (m_FramePointer * 4))
                    {
                        m_Hit = false;
                        xa = 0;
                        ya = 0;
                        this.setFrame(0);
                    }
                    else
                    {
                        this.setFrame(m_FrameIndex += m_FramePointer);
                    }
                }
            }
            else
            {
                int newFrame = m_FrameIndex + m_FramePointer;
                if (newFrame >= (4 * 4) + (m_FramePointer * 4))
                {
                    m_DeadAnimation = false;
                    xa = 0;
                    ya = 0;
                    this.setFrame(0);
                }
                else
                {
                    if (m_DeadAnimation)
                    {
                        this.setFrame(m_FrameIndex += m_FramePointer);
                    }
                }
            }
        }
        
        m_Timer++;
        m_DirectionTimer++;
        
        if (m_Health <= 0)
        {
            this.die();
        }
    }
    
    public boolean isAttacking()
    {
        return m_Attacking;
    }
    
    public boolean isAlive()
    {
        return m_Alive;
    }
}
