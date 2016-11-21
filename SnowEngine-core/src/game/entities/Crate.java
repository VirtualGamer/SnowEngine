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
import com.snowengine.objects.Level;
import com.snowengine.objects.entities.AnimatedEntity;

import java.util.Random;

public final class Crate extends AnimatedEntity
{
    private static AudioClip m_AudioClip;
    private AudioSource m_AudioSource;
    
    public Crate()
    {
        super ("textures/crate_sheet.png", 1, 3);
    
        if (m_AudioClip == null)
        {
            m_AudioClip = AudioMaster.loadAudioClip("audio/crate_destroy.wav");
        }
    
        m_AudioSource = new AudioSource();
    }
    
    @Override
    public void destroy()
    {
        if (this.parent instanceof Level)
        {
            Level level = (Level) this.parent;
            Random random = new Random();
            int chance = random.nextInt(4);
            if (chance == 3)
            {
                this.spawnCoins(level, random);
            }
        }
        
        super.destroy();
    }
    
    private void spawnCoins(Level level, Random random)
    {
        int amount = random.nextInt(5) + 2;
        for (int i = 0; i < amount; i++)
        {
            Coin coin = new Coin();
            float x = this.getPosition().getX();
            float y = this.getPosition().getY();
            x += random.nextInt(16) * 2;
            y += random.nextInt(16) * 2;
            coin.move(new Vector2(x, y));
            level.addEntity(coin);
        }
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    
        m_AudioSource.setPosition(this.transform.getPosition());
        m_AudioSource.play(m_AudioClip);
    }
}
