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
import com.snowengine.input.KeyCode;
import com.snowengine.input.Keyboard;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.AbstractGame;
import com.snowengine.objects.Camera2D;
import com.snowengine.objects.lighting.Light;
import com.snowengine.utils.FileUtils;
import com.snowengine.utils.files.TMXFile;

import java.util.Random;

public final class Game extends AbstractGame
{
    private Player player;
    private int timer = 0, maxTime = 10, frameIndex = 0;
    private AudioSource m_MusicSource;
    private AudioClip m_MusicClip;
    
    public Game()
    {
        super ("SnowEngine!", 960, 540, true);
    }
    
    @Override
    public void start()
    {
        TMXFile file = FileUtils.openTMXFile("maps/demo_map.tmx");
        this.setLevel(file.getLevel());
        
        this.setAmbientColor(new Vector3(0.4f, 0.2f, 0.2f));
        
        player = new Player();
        player.move(new Vector2(400, 400));
        this.add(player);
        
        Camera2D camera = new Camera2D();
        player.addChild(camera);
        
        Light light = new Light(new Vector2(), new Vector3(1.0f, 1.0f, 1.0f), 256.0f);
        player.addChild(light);
        
        Crate crate1 = new Crate();
        crate1.move(new Vector2(0, 256));
        this.add(crate1);
    
        Crate crate2 = new Crate();
        crate2.move(new Vector2(64, 256));
        this.add(crate2);
    
        Crate crate3 = new Crate();
        crate3.move(new Vector2(0, 320));
        this.add(crate3);
    
        Crate crate4 = new Crate();
        crate4.move(new Vector2(256, 320));
        this.add(crate4);
    
        Random random = new Random();
        for (int i = 0; i < 5; i++)
        {
            float x = 512 + (random.nextInt(16) * 3.2f) * i;
            float y = 320 + (random.nextInt(16) * 3.2f) * i;
            Coin coin = new Coin();
            coin.move(new Vector2(x, y));
            this.add(coin);
        }
        
        
        Slime slime = new Slime();
        slime.move(new Vector2(512, 574));
        this.add(slime);
        
        m_MusicClip = AudioMaster.loadAudioClip("audio/Tormented.wav");
        m_MusicSource = new AudioSource();
        m_MusicSource.loop(m_MusicClip);
        
        super.start();
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        m_MusicSource.stop();
        m_MusicClip.delete();
        m_MusicSource.delete();
    }
    
    @Override
    public void update()
    {
        if (timer >= maxTime)
        {
            timer = 0;
            player.setFrame((frameIndex < 6) ? frameIndex++ : (frameIndex = 0));
        }
        
        timer++;
        
        super.update();
        
        if (Keyboard.getKeyReleased(KeyCode.Escape))
        {
            this.stop();
        }
    }
    
    public static void main(String args[])
    {
        FileUtils.setPathPrefix(args[0]);
        new Game().start();
    }
}
