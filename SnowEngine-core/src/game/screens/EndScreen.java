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

public final class EndScreen extends Screen
{
    private GUIContainer m_TitleImage;
    private int m_Time, m_MaxTime;
    
    public EndScreen()
    {
        m_TitleImage = new GUIContainer("gui/logo.png", new Vector2(-128, -96));
        m_TitleImage.scale(-0.5f);
        m_Time = 0;
        m_MaxTime = 60;
    }
    
    @Override
    public void update()
    {
        if (m_Time == m_MaxTime)
        {
            m_Time = 0;
            Game.getGame().setGameState(GameState.MainMenu);
        }
        
        m_Time++;
        
        super.update();
    }
    
    @Override
    public void render()
    {
        guiLayer.add(m_TitleImage);
        
        super.render();
    }
}
