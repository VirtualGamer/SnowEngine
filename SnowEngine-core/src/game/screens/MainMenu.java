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

import com.snowengine.maths.Vector2;
import com.snowengine.objects.Screen;
import com.snowengine.objects.gui.GUIContainer;

public final class MainMenu extends Screen
{
    private GUIContainer m_TitleImage;
    
    public MainMenu()
    {
        m_TitleImage = new GUIContainer("gui/logo.png", new Vector2(64, -96));
        m_TitleImage.scale(-0.5f);
    }
    
    @Override
    public void render()
    {
        guiLayer.add(m_TitleImage);
        
        super.render();
    }
}
