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

import com.snowengine.graphics.Texture;
import com.snowengine.graphics.Window;
import com.snowengine.input.KeyCode;
import com.snowengine.input.Keyboard;
import com.snowengine.maths.Vector2;
import com.snowengine.maths.Vector3;
import com.snowengine.objects.Camera2D;
import com.snowengine.objects.Level;
import com.snowengine.objects.gui.Canvas;
import com.snowengine.objects.GameObject;
import com.snowengine.objects.entities.AnimatedEntity;
import com.snowengine.objects.gui.GUIContainer;
import com.snowengine.objects.gui.GUIContainerAnimated;
import com.snowengine.objects.gui.GUIText;
import com.snowengine.objects.lighting.Light;
import com.snowengine.objects.tiles.Tile;
import com.snowengine.objects.tiles.TileBase;
import game.Game;
import game.tiles.WallTile;

public class Player extends AnimatedEntity
{
    private Camera2D m_Camera;
    public float speed;
    private float m_Score, m_Health;
    private int m_Timer, m_MaxTime, m_FrameIndex;
    
    private GUIText m_ScoreText;
    private GUIContainer m_Portrait;
    private GUIContainerAnimated m_HealthBar;
    
    public Player()
    {
        super ("textures/player.png", 1, 7);
        super.setBounds(0, 32, 16, 16);
    
        m_Camera = new Camera2D();
        this.addChild(m_Camera);
    
        Light light = new Light(new Vector2(), new Vector3(1.0f, 1.0f, 1.0f), 256.0f);
        this.addChild(light);
        
        this.speed = 5;
        m_Score = 0;
        m_Timer = 0;
        m_MaxTime = 5;
        m_FrameIndex = 0;
        m_Health = 100;
    
        m_ScoreText = new GUIText("Score 0", new Vector2(0, 14));
        m_Portrait = new GUIContainer("gui/portrait.png", new Vector2(-284, -292));
        m_Portrait.scale(-0.9f);
        m_HealthBar = new GUIContainerAnimated("gui/healthbar.png", 1, 11, new Vector2(75, 46));
        m_HealthBar.setFrame(10);
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
    
    @Override
    public void onCollision(GameObject other)
    {
        super.onCollision(other);
        boolean attack = Keyboard.getKeyPressed(KeyCode.Space);
        
        if (other instanceof Slime)
        {
            Slime slime = (Slime) other;
    
            if (attack)
            {
                slime.hit(this);
            }
            
            m_Health -= 0.25f;
        }
        else if (other instanceof Crate)
        {
            Crate crate = (Crate) other;
            float xa = 0, ya = 0, speed = 1;
            Vector2 pos = this.getPosition(), opos = crate.getPosition();
            
            if (pos.getY() > (opos.getY() - 1))
            {
                ya = speed;
            }
            else if (pos.getY() < (opos.getY() + 1))
            {
                ya = -speed;
            }
            if (pos.getX() < (opos.getX() - 1))
            {
                xa = speed;
            }
            else if (pos.getX() > (opos.getX() + 1))
            {
                xa = -speed;
            }
            
            this.move(new Vector2(xa, ya));
            
            if (attack)
            {
                crate.destroy();
            }
        }
        
        if (other instanceof Tile)
        {
            Tile tile = (Tile) other;
            if (tile.isSolid())
            {
                float xa = 0, ya = 0, speed = 1;
                Vector2 pos = this.getPosition(), opos = tile.getPosition();
    
                if (pos.getY() > (opos.getY() - 1))
                {
                    ya = speed;
                }
                else if (pos.getY() < (opos.getY() + 1))
                {
                    ya = -speed;
                }
                if (pos.getX() < (opos.getX() - 1))
                {
                    xa = speed;
                }
                else if (pos.getX() > (opos.getX() + 1))
                {
                    xa = -speed;
                }
    
                this.move(new Vector2(xa, ya));
            }
        }
    }
    
    public void setScore(float score)
    {
        m_Score = score;
    }
    
    public void addScore(float score)
    {
        m_Score += score;
    }
    
    public void removeScore(float score)
    {
        if (m_Score > score)
        {
            m_Score -= score;
        }
        else
        {
            this.setScore(0);
        }
    }
    
    @Override
    public void move(Vector3 vector)
    {
        super.move(vector);
    }
    
    @Override
    public void update()
    {
        Vector2 horSpeed = new Vector2(this.speed, 0);
        Vector2 verSpeed = new Vector2(0, this.speed);
        
        if (Keyboard.getKey(KeyCode.W))
        {
            this.move(verSpeed.negate());
        }
        if (Keyboard.getKey(KeyCode.S))
        {
            this.move(verSpeed);
        }
    
        if (Keyboard.getKey(KeyCode.A))
        {
            this.move(horSpeed.negate());
        }
        if (Keyboard.getKey(KeyCode.D))
        {
            this.move(horSpeed);
        }
        
        if (m_Health < 0)
        {
            Game.getGame().stop();
        }
        
        m_ScoreText.setText("Score " + ((int) m_Score));
        m_HealthBar.setFrame(Math.round(m_Health / 10.0f));
    
        super.update();
    }
    
    @Override
    public void render()
    {
        if (m_Timer >= m_MaxTime)
        {
            m_Timer = 0;
            this.setFrame((m_FrameIndex < 6) ? m_FrameIndex++ : (m_FrameIndex = 0));
            
            if (m_Health < 100)
            {
                m_Health++;
            }
        }
    
        m_Timer++;
        
        super.render();
    
        Canvas canvas = m_Camera.getCanvas();
        if (canvas != null)
        {
            canvas.draw(m_ScoreText);
            canvas.drawImage(m_Portrait);
            canvas.drawImage(m_HealthBar);
        }
        else
        {
            if (this.parent instanceof Level)
            {
                Level level = (Level) this.parent;
                canvas = level.getCanvas();
                canvas.move(this.getPosition());
                m_Camera.setCanvas(canvas);
            }
        }
    }
}
