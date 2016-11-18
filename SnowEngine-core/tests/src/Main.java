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

import com.snowengine.graphics.*;
import com.snowengine.input.*;
import com.snowengine.maths.*;
import com.snowengine.objects.*;
import com.snowengine.objects.entities.AnimatedEntity;
import com.snowengine.objects.lighting.*;
import com.snowengine.objects.sprites.*;
import com.snowengine.objects.tiles.AnimatedTile;
import com.snowengine.objects.tiles.Tile;
import com.snowengine.utils.*;

public final class Main
{
    public static void main(String args[])
    {
        FileUtils.setPathPrefix(args[0]);
        
        Window window = new Window("SnowEngine!", 0, 0, true);
        
        Level level = new Level();
        level.setAmbientLight(new Vector3(0.2f, 0.1f, 0.1f));
    
        AnimatedEntity player = new AnimatedEntity("textures/player.png", 1, 7);
        level.addEntity(player);
        
        Camera2D camera = new Camera2D();
        player.addChild(camera);
    
        Light light = new Light(new Vector2(), new Vector3(0.1f, 0.5f, 0.3f), 128.0f);
        player.addChild(light);
    
        Tile tile1 = new Tile("textures/ground_tile.png");
        tile1.scale(new Vector2(50, 50));
        level.addTile(tile1);
    
        AnimatedEntity entity2 = new AnimatedEntity("textures/crate_sheet.png", 1, 3);
        entity2.move(new Vector2(-128, -128));
        level.addEntity(entity2);
        
        AnimatedEntity entity3 = new AnimatedEntity("textures/coins_crate.png", 1, 4);
        entity3.move(new Vector2(-256, -256));
        level.addEntity(entity3);
    
        window.showCursor(false);
        window.setVisible(true);
        
        int timer = 0, maxTime = 10, frameIndex = 0;
        int timer2 = 0, maxTime2 = 10, frameIndex2 = 0;
        int timer3 = 0, maxTime3 = 3, frameIndex3 = 0, framePointer = 1;
        Vector3 horSpeed = new Vector3(5, 0, 0), verSpeed = new Vector3(0, 5, 0);
        while (!window.isCloseRequested())
        {
            if (timer >= maxTime)
            {
                timer = 0;
                player.setFrame((frameIndex < 6) ? frameIndex++ : (frameIndex = 0));
            }
            if (timer2 >= maxTime2)
            {
                timer2 = 0;
                entity2.setFrame((frameIndex2 < 3) ? frameIndex2++ : (frameIndex2 = 0));
            }

            if (timer3 >= maxTime3)
            {
                timer3 = 0;
                int newFrame = frameIndex3 + framePointer;
                if (newFrame < 0 || newFrame >= 4)
                {
                    framePointer = -framePointer;
                }
                entity3.setFrame(frameIndex3 += framePointer);
            }

            window.clear();
    
            level.update();
            level.render();

            window.update();
            timer++;
            timer2++;
            timer3++;
            
            if (!player.isColliding(entity2))
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
                window.requestClose();
            }
        }

        window.destroy();
    }
}
