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
package game;

import com.snowengine.input.KeyCode;
import com.snowengine.input.Keyboard;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.AbstractGame;
import com.snowengine.utils.ColorUtils;
import com.snowengine.utils.FileUtils;
import com.snowengine.utils.files.TMXFile;
import game.commands.Command;
import game.commands.CommandDialog;
import game.entities.Coin;
import game.entities.Crate;
import game.entities.Player;
import game.entities.Spider;
import game.screens.ControlMenu;
import game.screens.MainMenu;

import java.util.Random;

public final class Game extends AbstractGame
{
    private static Game theGame;
    private Player player;
    private GameState m_GameState;
    private MainMenu m_MainMenu;
    private ControlMenu m_ControlMenu;
    private MusicPlayer m_MusicPlayer;
    
    public Game()
    {
        super ("Spiral Knights", 1600, 1200, false);
        theGame = this;
    }
    
    @Override
    public void start()
    {
        TMXFile file = FileUtils.openTMXFile("maps/demo_map.tmx");
        this.setLevel(file.getLevel());

        this.setAmbientColor(new Vector3(0.3f, 0.2f, 0.2f));

        player = new Player();
        player.move(new Vector2(400, 400));
        this.add(player);

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
            Spider slime = new Spider();
            float x = 512 + (random.nextInt(32) * 8.5f) * i;
            float y = 574 + (random.nextInt(32) * 8.5f) * i;
            slime.move(new Vector2(x, y));
            this.add(slime);
        }
    
        m_GameState = GameState.MainMenu;
        m_MainMenu = new MainMenu();
        m_ControlMenu = new ControlMenu();
        m_MusicPlayer = new MusicPlayer();
        
        super.start();
    }
    
    public void setGameState(GameState gameState)
    {
        m_GameState = gameState;
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
        if (m_GameState == GameState.MainMenu)
        {
            m_MainMenu.update();
        }
        else if (m_GameState == GameState.Controls)
        {
            m_ControlMenu.update();
        }
        else if (m_GameState == GameState.InGame)
        {
            super.update();
            
            if (Keyboard.getKeyPressed(KeyCode.GraveAccent))
            {
                new CommandDialog();
            }
            
            if (Keyboard.getKeyPressed(KeyCode.Escape))
            {
                this.setGameState(GameState.MainMenu);
            }
        }
        
        if (!m_MusicPlayer.isPlayingMusic())
        {
            m_MusicPlayer.playNextSong();
        }
    }
    
    @Override
    public void render()
    {
        if (m_GameState == GameState.MainMenu)
        {
            m_MainMenu.render();
        }
        else if (m_GameState == GameState.Controls)
        {
            m_ControlMenu.render();
        }
        else if (m_GameState == GameState.InGame)
        {
            super.render();
        }
    }
    
    public GameState getGameState()
    {
        return m_GameState;
    }
    
    public Player getPlayer()
    {
        return this.player;
    }
    
    public static Game getGame()
    {
        return theGame;
    }
    
    public static void main(String args[])
    {
        FileUtils.setPathPrefix(args[0]);
        new Game().start();
    }
}
