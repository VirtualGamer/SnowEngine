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
import com.snowengine.objects.entities.AnimatedEntity;
import com.snowengine.objects.lighting.Light;
import com.snowengine.objects.tiles.Tile;
import com.snowengine.utils.FileUtils;

public final class Game extends AbstractGame
{
    private AnimatedEntity player, crate, coin;
    private int timer = 0, maxTime = 10, frameIndex = 0;
    private int timer2 = 0, maxTime2 = 10, frameIndex2 = 0;
    private int timer3 = 0, maxTime3 = 3, frameIndex3 = 0, framePointer = 1;
    private Vector3 horSpeed = new Vector3(5, 0, 0), verSpeed = new Vector3(0, 5, 0);
    private AudioSource m_MusicSource;
    private AudioClip m_MusicClip;
    
    public Game()
    {
        super ("SnowEngine!", 960, 540, true);
    }
    
    @Override
    public void start()
    {
        this.setAmbientColor(new Vector3(0.3f, 0.2f, 0.1f));
        
        player = new AnimatedEntity("textures/player.png", 1, 7);
        this.add(player);
        
        Camera2D camera = new Camera2D();
        player.addChild(camera);
        
        Light light = new Light(new Vector2(), new Vector3(0.1f, 0.5f, 0.3f), 128.0f);
        player.addChild(light);
        
        crate = new AnimatedEntity("textures/crate_sheet.png", 1, 3);
        crate.move(new Vector2(-128, -128));
        this.add(crate);
        
        coin = new AnimatedEntity("textures/coin.png", 1, 4);
        coin.move(new Vector2(-256, -256));
        this.add(coin);
        
        Tile groundTile = new Tile("textures/ground_tile.png");
        groundTile.scale(new Vector2(50, 50));
        this.add(groundTile);
        
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
        if (timer2 >= maxTime2)
        {
            timer2 = 0;
            crate.setFrame((frameIndex2 < 3) ? frameIndex2++ : (frameIndex2 = 0));
        }
        
        if (timer3 >= maxTime3)
        {
            timer3 = 0;
            int newFrame = frameIndex3 + framePointer;
            if (newFrame < 0 || newFrame >= 4)
            {
                framePointer = -framePointer;
            }
            coin.setFrame(frameIndex3 += framePointer);
        }
        
        timer++;
        timer2++;
        timer3++;
        
        if (!player.isColliding(crate))
        {
            if (Keyboard.getKey(KeyCode.Up))
            {
                player.move(verSpeed.negate());
            }
            if (Keyboard.getKey(KeyCode.Down))
            {
                player.move(verSpeed);
            }
            
            if (Keyboard.getKey(KeyCode.Left))
            {
                player.move(horSpeed.negate());
            }
            if (Keyboard.getKey(KeyCode.Right))
            {
                player.move(horSpeed);
            }
        }
        else
        {
            player.move(new Vector2(-1, -1));
        }
        
        if (Keyboard.getKeyReleased(KeyCode.Escape))
        {
            this.stop();
        }
        
        super.update();
    }
    
    public static void main(String args[])
    {
        FileUtils.setPathPrefix(args[0]);
        new Game().start();
    }
}
