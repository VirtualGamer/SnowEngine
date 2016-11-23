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
package game.screens;

import com.snowengine.input.KeyCode;
import com.snowengine.input.Keyboard;
import com.snowengine.maths.Vector2;
import com.snowengine.objects.Screen;
import com.snowengine.objects.gui.GUIButton;
import com.snowengine.objects.gui.GUIContainer;
import game.Game;
import game.GameState;

public final class MainMenu extends Screen
{
    private GUIContainer m_TitleImage;
    private GUIButton m_Play, m_Controls, m_Quit;
    
    public MainMenu()
    {
        m_TitleImage = new GUIContainer("gui/logo.png", new Vector2(-64, -96));
        m_TitleImage.scale(-0.5f);
        m_Play = new GUIButton("gui/buttons/play.png", new Vector2(608, 512));
        m_Play.scale(-0.25f);
        m_Play.setSelected(true);
        m_Controls = new GUIButton("gui/buttons/controls.png", new Vector2(608, 640));
        m_Controls.scale(-0.25f);
        m_Quit = new GUIButton("gui/buttons/quit.png", new Vector2(608, 768));
        m_Quit.scale(-0.25f);
    }
    
    @Override
    public void update()
    {
        m_Play.update();
        m_Controls.update();
        m_Quit.update();
        
        if (Keyboard.getKeyPressed(KeyCode.W))
        {
            if (m_Play.isSelected())
            {
                m_Play.setSelected(false);
                m_Controls.setSelected(false);
                m_Quit.setSelected(true);
            }
            else if (m_Controls.isSelected())
            {
                m_Play.setSelected(true);
                m_Controls.setSelected(false);
                m_Quit.setSelected(false);
            }
            else if (m_Quit.isSelected())
            {
                m_Play.setSelected(false);
                m_Controls.setSelected(true);
                m_Quit.setSelected(false);
            }
        }
        else if (Keyboard.getKeyPressed(KeyCode.S))
        {
            if (m_Play.isSelected())
            {
                m_Play.setSelected(false);
                m_Controls.setSelected(true);
                m_Quit.setSelected(false);
            }
            else if (m_Controls.isSelected())
            {
                m_Play.setSelected(false);
                m_Controls.setSelected(false);
                m_Quit.setSelected(true);
            }
            else if (m_Quit.isSelected())
            {
                m_Play.setSelected(true);
                m_Controls.setSelected(false);
                m_Quit.setSelected(false);
            }
        }
        
        if (Keyboard.getKeyPressed(KeyCode.F))
        {
            Game game = Game.getGame();
            if (m_Play.isSelected())
            {
                game.setGameState(GameState.InGame);
            }
            else if (m_Controls.isSelected())
            {
                game.setGameState(GameState.Controls);
            }
            else if (m_Quit.isSelected())
            {
                game.stop();
            }
        }
        
        super.update();
    }
    
    @Override
    public void render()
    {
        guiLayer.add(m_TitleImage);
        guiLayer.add(m_Play);
        guiLayer.add(m_Controls);
        guiLayer.add(m_Quit);
        
        super.render();
    }
}
