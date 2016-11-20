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

import com.snowengine.input.KeyCode;
import com.snowengine.input.Keyboard;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.AbstractGame;
import com.snowengine.objects.Camera2D;
import com.snowengine.objects.Font;
import com.snowengine.objects.lighting.Light;
import com.snowengine.utils.FileUtils;
import com.snowengine.utils.files.TMXFile;
import entities.Coin;
import entities.Crate;
import entities.Player;
import entities.Slime;

import java.util.Random;

public final class Game extends AbstractGame
{
    private Player player;
    private MusicPlayer m_MusicPlayer;
    private Font m_Font;
    
    public Game()
    {
        super ("SnowEngine!", 960, 540, true );
    }
    
    @Override
    public void start()
    {
        TMXFile file = FileUtils.openTMXFile("maps/demo_map.tmx");
        this.setLevel(file.getLevel());
        
        this.setAmbientColor(new Vector3(0.25f, 0.1f, 0.1f));
        
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
        
        for (int i = 0; i < 10; i++)
        {
            Slime slime = new Slime();
            float x = 512 + (random.nextInt(32) * 8.5f) * i;
            float y = 574 + (random.nextInt(32) * 8.5f) * i;
            slime.move(new Vector2(x, y));
            this.add(slime);
        }
    
        m_MusicPlayer = new MusicPlayer();
        m_Font = new Font("fonts/test_font.png");
        this.setFont(m_Font);
        
        super.start();
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        
        m_MusicPlayer.stop();
        m_MusicPlayer.delete();
    }
    
    @Override
    public void update()
    {
        super.update();
        
        if (!m_MusicPlayer.isPlayingMusic())
        {
            m_MusicPlayer.playNextSong();
        }
        
        if (Keyboard.getKeyPressed(KeyCode.R))
        {
            m_MusicPlayer.stop();
        }
        
        if (Keyboard.getKeyReleased(KeyCode.Escape))
        {
            this.stop();
        }
    }
    
    @Override
    public void render()
    {
        super.render();
        m_Font.drawString("Use the arrow keys to\ncontrol the character", new Vector2(1, 1000), false);
    }
    
    public static void main(String args[])
    {
        FileUtils.setPathPrefix(args[0]);
        new Game().start();
    }
}
