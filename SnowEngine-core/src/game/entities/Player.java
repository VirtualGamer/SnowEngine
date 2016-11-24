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
import com.snowengine.objects.sprites.Sprite;
import com.snowengine.objects.tiles.Tile;
import game.Game;

public class Player extends AnimatedEntity
{
    private Camera2D m_Camera;
    public float speed;
    private float m_Score, m_Health;
    private int m_HealTimer, m_MaxHealTime, m_FrameIndex;
    private boolean m_Walking, m_Blocking, m_Attacking, m_Forward;
    
    private Sprite m_Shadow;
    
    private GUIText m_ScoreText;
    private GUIContainer m_Portrait;
    private GUIContainerAnimated m_HealthBar;
    
    public Player()
    {
        super ("textures/player.png", 12, 11);
        super.setBounds(0, 32, 32, 32);
        
        m_Shadow = new Sprite("textures/shadow.png");
        m_Shadow.move(new Vector2(0, 48));
        
        m_Camera = new Camera2D();
        m_Camera.addChild(m_Shadow);
        this.addChild(m_Camera);
        
        Light light = new Light(new Vector2(), new Vector3(1.0f, 1.0f, 1.0f), 256.0f);
        this.addChild(light);
        
        this.speed = 5;
        m_Score = 0;
        m_HealTimer = 0;
        m_MaxHealTime = 5;
        m_FrameIndex = 0;
        m_Health = 100;
        m_Walking = false;
        m_Forward = true;
        
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
        boolean attack = Keyboard.getKeyPressed(KeyCode.F) && !m_Blocking;
        
        if (other instanceof Spider)
        {
            Spider slime = (Spider) other;
            
            if (attack)
            {
                slime.hit(this);
            }
            
            if (!m_Blocking)
            {
                m_Health -= 0.25f;
            }
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
    
    public void setHealth(float health)
    {
        m_Health = health;
    }
    
    public void addHealth(float health)
    {
        m_Health += health;
    }
    
    public void removeHealth(float health)
    {
        if (m_Health > health)
        {
            m_Health -= health;
        }
        else
        {
            this.setHealth(0);
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
        float xa = 0, ya = 0;
        m_Walking = false;
        m_Blocking = false;
        m_Attacking = false;
        
        if (Keyboard.getKey(KeyCode.W))
        {
            ya = -1;
            m_Forward = true;
        }
        if (Keyboard.getKey(KeyCode.S))
        {
            ya = 1;
            m_Forward = false;
        }
        
        if (Keyboard.getKey(KeyCode.A))
        {
            xa = -1;
            if (this.getScale().x > 0)
            {
                this.scale(new Vector2(-2, 0));
            }
        }
        if (Keyboard.getKey(KeyCode.D))
        {
            xa = 1;
            if (this.getScale().x < 0)
            {
                this.scale(new Vector2(2, 0));
            }
        }
        
        if (Keyboard.getKey(KeyCode.G))
        {
            m_Blocking = true;
        }
        
        if (Keyboard.getKeyPressed(KeyCode.F) && !m_Blocking)
        {
            m_Attacking = true;
            if (m_Forward)
            {
                this.setFrame(2);
            }
            else
            {
                this.setFrame(3);
            }
        }
        
        if ((xa != 0 || ya != 0) && !m_Attacking)
        {
            this.transform.move(xa * this.speed, ya * this.speed, 0);
            m_Walking = true;
        }
        
        if (m_Health < 0)
        {
            Game.getGame().stop();
        }
        
        m_ScoreText.setText("Score " + ((int) m_Score));
        
        if (m_Health < 100)
        {
            m_HealthBar.setFrame(Math.round(m_Health / 10.0f));
        }
        else
        {
            m_HealthBar.setFrame(10);
        }
        
        super.update();
    }
    
    @Override
    public void render()
    {
        if (m_HealTimer >= m_MaxHealTime)
        {
            m_HealTimer = 0;
            
            if (m_Health < 100)
            {
                m_Health++;
            }
        }
        
        m_HealTimer++;
        
        super.render();
        
        if (m_Blocking)
        {
            if (m_Forward)
            {
                this.setFrame(0);
            }
            else
            {
                this.setFrame(1);
            }
        }
        else
        {
            if (m_Forward)
            {
                this.setFrame(4);
            }
            else
            {
                this.setFrame(5);
            }
        }
        
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
