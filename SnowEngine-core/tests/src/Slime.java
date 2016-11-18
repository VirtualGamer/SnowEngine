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

import com.snowengine.audio.AudioClip;
import com.snowengine.audio.AudioMaster;
import com.snowengine.audio.AudioSource;
import com.snowengine.maths.Vector2;
import com.snowengine.objects.Level;
import com.snowengine.objects.entities.AnimatedEntity;
import com.snowengine.objects.entities.EntityBase;

import java.util.Random;

public final class Slime extends AnimatedEntity
{
    private static AudioClip m_AudioClip;
    private AudioSource m_AudioSource;
    private int m_Health, m_Timer, m_MaxTime, m_FrameIndex, m_FramePointer;
    private float xa = 0, ya = 0;
    
    public Slime()
    {
        super ("textures/slime.png", 1, 3);
    
        if (m_AudioClip == null)
        {
            m_AudioClip = AudioMaster.loadAudioClip("audio/enemy_hit.wav");
        }
    
        m_AudioSource = new AudioSource();
        m_Health = 5 + new Random().nextInt(3);
        m_Timer = 0;
        m_MaxTime = 10;
        m_FrameIndex = 0;
        m_FramePointer = 1;
    }
    
    public void destroy()
    {
        super.destroy();
    }
    
    public void onDestroy()
    {
        if (this.parent instanceof Level)
        {
            Level level = (Level) this.parent;
            Random random = new Random();
            int chance = random.nextInt(3);
            if (chance % 3 > 0)
            {
                int amount = random.nextInt();
                for (int i = 0; i < amount; i++)
                {
                    Coin coin = new Coin();
                    coin.move(new Vector2(random.nextFloat() * 0.5f, random.nextFloat() * 0.5f));
                    level.addEntity(coin);
                }
            }
        }
        
        super.onDestroy();
    }
    
    public void hit(EntityBase attacker)
    {
        m_AudioSource.setPosition(this.transform.getPosition());
        m_AudioSource.play(m_AudioClip);
        m_Health -= 1;
    }
    
    @Override
    public void update()
    {
        super.update();
    
        if (m_Timer >= m_MaxTime)
        {
            m_Timer = 0;
            int newFrame = m_FrameIndex + m_FramePointer;
            if (newFrame < 0 || newFrame >= 3)
            {
                m_FramePointer = -m_FramePointer;
            }
            this.setFrame(m_FrameIndex += m_FramePointer);
    
            int rand1 = new Random().nextInt(4);
            int rand2 = new Random().nextInt(4);
    
            if (rand1 == 0)
            {
                xa = 1;
            }
            else if (rand1 == 1)
            {
                xa = -1;
            }
            else
            {
                xa = 0;
            }
    
            if (rand2 == 0)
            {
                ya = 1;
            }
            else if (rand2 == 2)
            {
                ya = -1;
            }
            else
            {
                ya = 0;
            }
        }
    
        this.move(new Vector2(xa, ya));
        
        m_Timer++;
        
        if (m_Health <= 0)
        {
            this.destroy();
        }
    }
}
