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

import com.snowengine.audio.AudioClip;
import com.snowengine.audio.AudioMaster;
import com.snowengine.audio.AudioSource;
import com.snowengine.input.KeyCode;
import com.snowengine.input.Keyboard;
import com.snowengine.maths.Vector2;
import com.snowengine.objects.Screen;
import com.snowengine.objects.gui.GUIButton;
import com.snowengine.objects.gui.GUIContainer;
import game.Game;
import game.GameState;

public final class ControlMenu extends Screen
{
    private GUIContainer m_TitleImage, m_ButtonLayout;
    private GUIButton m_Back;
    private AudioClip m_ButtonClick;
    private AudioSource m_AudioSource;
    
    public ControlMenu()
    {
        m_TitleImage = new GUIContainer("gui/logo.png", new Vector2(-128, -96));
        m_TitleImage.scale(-0.5f);
        m_ButtonLayout = new GUIContainer("gui/buttons/button_layout.png", new Vector2(576, 544));
        m_Back = new GUIButton("gui/buttons/back.png", new Vector2(544, 896));
        m_Back.scale(-0.25f);
        m_Back.setSelected(true);
        m_ButtonClick = AudioMaster.loadAudioClip("audio/button_click.wav");
        m_AudioSource = new AudioSource();
    }
    
    @Override
    public void update()
    {
        m_Back.update();
        
        if (Keyboard.getKeyPressed(KeyCode.F))
        {
            m_AudioSource.play(m_ButtonClick);
            Game game = Game.getGame();
            if (m_Back.isSelected())
            {
                game.setGameState(GameState.MainMenu);
            }
        }
        
        super.update();
    }
    
    @Override
    public void render()
    {
        guiLayer.add(m_TitleImage);
        guiLayer.add(m_ButtonLayout);
        guiLayer.add(m_Back);
        
        super.render();
    }
}
