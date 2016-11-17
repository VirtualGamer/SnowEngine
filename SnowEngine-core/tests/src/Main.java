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

import com.snowengine.audio.*;
import com.snowengine.graphics.*;
import com.snowengine.input.*;
import com.snowengine.maths.*;
import com.snowengine.objects.*;
import com.snowengine.objects.lighting.*;
import com.snowengine.objects.sprites.*;
import com.snowengine.utils.*;

public final class Main
{
    public static void main(String args[])
    {
        FileUtils.setPathPrefix(args[0]);
        
        Window window = new Window("SnowEngine!", 0, 0, true);
    
        AmbientLight ambient = AmbientLight.create(new Vector3(0.2f, 0.1f, 0.1f));
        
        Camera2D camera = new Camera2D();
    
        Light light = new Light(new Vector2(), new Vector3(0.1f, 0.5f, 0.3f), 128.0f);
        camera.addChild(light);
    
        AnimatedSprite sprite1 = new AnimatedSprite("textures/player.png", 1, 7);
        camera.addChild(sprite1);
    
        Sprite sprite2 = new Sprite("textures/ground_tile.png");
        sprite2.scale(new Vector2(50, 50));
        
        AnimatedSprite sprite3 = new AnimatedSprite("textures/crate_sheet.png", 1, 3);
        sprite3.move(new Vector2(-128, -128));
        
        AnimatedSprite sprite4 = new AnimatedSprite("textures/coins_crate.png", 1, 4);
        sprite4.move(new Vector2(-256, -256));

        Shader shader = new Shader();
        shader.addVertexShader("shaders/basic.vert");
        shader.addFragmentShader("shaders/basic.frag");
        shader.compile();
        shader.enable();

        AudioClip clip = AudioMaster.loadAudioClip("audio/bounce.wav");
        AudioClip clip1 = AudioMaster.loadAudioClip("audio/music.wav");
        AudioClip clip2 = AudioMaster.loadAudioClip("audio/music2.wav");
        AudioClip clip3 = AudioMaster.loadAudioClip("audio/music3.wav");

        AudioMaster.setDistanceModel(DistanceModel.LinearDistanceClamped);
        AudioSource source = new AudioSource();
        source.setReferenceDistance(256);
        source.setMaxDistance(512);
        source.play(clip);
    
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
                sprite1.setFrame((frameIndex < 6) ? frameIndex++ : (frameIndex = 0));
            }
            if (timer2 >= maxTime2)
            {
                timer2 = 0;
                sprite3.setFrame((frameIndex2 < 3) ? frameIndex2++ : (frameIndex2 = 0));
            }

            if (timer3 >= maxTime3)
            {
                timer3 = 0;
                int newFrame = frameIndex3 + framePointer;
                if (newFrame < 0 || newFrame >= 4)
                {
                    framePointer = -framePointer;
                }
                sprite4.setFrame(frameIndex3 += framePointer);
            }

            window.clear();
    
            camera.update();
    
            ambient.render();
            sprite2.render();
            sprite3.render();
            sprite4.render();
            camera.render();

            window.update();
            timer++;
            timer2++;
            timer3++;
            
            if (!sprite1.isColliding(sprite3))
            {
                if (Keyboard.getKey(KeyCode.Up))
                {
                    camera.move(verSpeed.negate());
                }
                if (Keyboard.getKey(KeyCode.Down))
                {
                    camera.move(verSpeed);
                }
    
                if (Keyboard.getKey(KeyCode.Left))
                {
                    camera.move(horSpeed.negate());
                }
                if (Keyboard.getKey(KeyCode.Right))
                {
                    camera.move(horSpeed);
                }
            }
            else
            {
                camera.move(new Vector2(-1, -1));
            }

            if (Keyboard.getKeyPressed(KeyCode.F) && source.isStopped())
            {
                source.play(clip1);
            }
            if (Keyboard.getKeyPressed(KeyCode.G) && source.isStopped())
            {
                source.play(clip2);
            }
            if (Keyboard.getKeyPressed(KeyCode.H) && source.isStopped())
            {
                source.play(clip3);
            }
            if (Keyboard.getKeyPressed(KeyCode.J) && source.isStopped())
            {
                source.loop(clip);
            }
            if (Keyboard.getKeyPressed(KeyCode.D) && source.isPlaying())
            {
                source.stop();
            }

            if (Keyboard.getKeyReleased(KeyCode.Escape))
            {
                window.requestClose();
            }
        }

        source.delete();
        shader.destroy();
        window.destroy();
    }
}
