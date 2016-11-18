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
package com.snowengine.objects;

import com.snowengine.graphics.Window;
import com.snowengine.objects.entities.EntityBase;
import com.snowengine.objects.lighting.Light;
import com.snowengine.objects.tiles.TileBase;

public abstract class AbstractGame extends GameObject
{
    private Window m_Window;
    private Level m_Level;
    
    public AbstractGame(String title, int width, int height)
    {
        this (title, width, height, false);
    }
    
    public AbstractGame(String title, int width, int height, boolean fullScreen)
    {
        this (title, width, height, fullScreen, true);
    }
    
    public AbstractGame(String title, int width, int height, boolean fullScreen, boolean vSync)
    {
        super ("Game", 0);
        
        m_Window = new Window(title, width, height, fullScreen, vSync);
        m_Level = new Level();
    }
    
    public void start()
    {
        this.run();
    }
    
    public void stop()
    {
        m_Window.requestClose();
    }
    
    @Override
    public void onDestroy()
    {
        m_Level.destroy();
        m_Window.destroy();
    }
    
    private void run()
    {
        m_Window.showCursor(false);
        m_Window.setVisible(true);
        
        while (!m_Window.isCloseRequested())
        {
            m_Window.clear();
            this.update();
            this.render();
            m_Window.update();
        }
        
        this.destroy();
    }
    
    public void add(TileBase tile)
    {
        m_Level.addTile(tile);
    }
    
    public void add(Light light)
    {
        m_Level.addLight(light);
    }
    
    public void add(EntityBase entity)
    {
        m_Level.addEntity(entity);
    }
    
    @Override
    public void update()
    {
        m_Level.update();
    }
    
    @Override
    public void render()
    {
        m_Level.render();
    }
}
