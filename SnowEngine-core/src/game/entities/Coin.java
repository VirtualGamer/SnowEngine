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

public final class Coin extends AnimatedEntity
{
    private static AudioClip m_AudioClip;
    private AudioSource m_AudioSource;
    private int m_Timer, m_MaxTime, m_FrameIndex, m_FramePointer;
    
    public Coin()
    {
        super ("textures/coin.png", 1, 4);
        this.scale(new Vector2(0.2f, 0.2f));
        
        if (m_AudioClip == null)
        {
            m_AudioClip = AudioMaster.loadAudioClip("audio/coin_pickup2.wav");
        }
        
        m_AudioSource = new AudioSource();
    
        m_Timer = 0;
        m_MaxTime = 5;
        m_FrameIndex = 0;
        m_FramePointer = 1;
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        
        m_AudioSource.setPosition(this.transform.getPosition());
        m_AudioSource.play(m_AudioClip);
    }
    
    @Override
    public void onCollision(GameObject other)
    {
        super.onCollision(other);
        
        if (other instanceof Player)
        {
            Player player = (Player) other;
            player.addScore(1);
            this.destroy();
        }
    }
   
    @Override
    public void update()
    {
        if (m_Timer >= m_MaxTime)
        {
            m_Timer = 0;
            int newFrame = m_FrameIndex + m_FramePointer;
            if (newFrame < 0 || newFrame >= 4)
            {
                m_FramePointer = -m_FramePointer;
            }
            this.setFrame(m_FrameIndex += m_FramePointer);
        }
    
        super.update();
    }
}
