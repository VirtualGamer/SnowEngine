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
import com.snowengine.objects.entities.AnimatedEntity;
import com.snowengine.objects.entities.EntityBase;

import java.util.Random;

public final class Spider extends AnimatedEntity
{
    private static AudioClip m_AudioClip;
    private AudioSource m_AudioSource;
    private int m_Health, m_Timer, m_MaxTime, m_FrameIndex, m_FramePointer;
    private boolean m_Moving, m_Hit;
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
        m_FramePointer = 4;
    }
    
    public void hit(EntityBase attacker)
    {
        if (attacker instanceof Player)
        {
            Player player = (Player) attacker;
            player.addScore(1);
        }
        
        m_Hit = true;
        xa = 0;
        ya = 0;
        m_AudioSource.setPosition(this.transform.getPosition());
        m_AudioSource.play(m_AudioClip);
        m_Health--;
    }
    
    @Override
    public void update()
    {
        super.update();
    
        if ((m_Timer >= m_MaxTime))
        {
            m_Timer = 0;
            if (!m_Hit)
            {
                int newFrame = m_FrameIndex + m_FramePointer;
                if ((newFrame < 0 || newFrame >= (8 * m_FramePointer)) && m_Moving)
                {
                    m_FramePointer = -m_FramePointer;
                }
                this.setFrame(m_FrameIndex += m_FramePointer);
                m_Moving = false;
    
                int rand1 = new Random().nextInt(4);
                int rand2 = new Random().nextInt(4);
    
                if (rand1 == 0)
                {
                    xa = 1;
                    m_Moving = true;
                    if (this.getScale().x > 0)
                    {
                        this.scale(new Vector2(-2, 0));
                    }
                }
                else if (rand1 == 1)
                {
                    xa = -1;
                    m_Moving = true;
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
                    m_Moving = true;
                }
                else if (rand2 == 1)
                {
                    ya = -1;
                    m_Moving = true;
                }
                else
                {
                    ya = 0;
                }
            }
            else
            {
                m_Hit = false;
            }
            
            this.move(new Vector2(xa, ya));
        }
        
        m_Timer++;
        
        if (m_Health <= 0)
        {
            this.destroy();
        }
    }
}
