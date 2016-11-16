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
import com.snowengine.utils.*;

public final class Main
{
    public static void main(String args[])
    {
        FileUtils.setPathPrefix("./SnowEngine-core/assets/");
        Window window = new Window("SnowEngine!", 800, 600);
        window.setClearColor(new Vector3(0.25f, 0.5f, 0.75f));
        
        Camera2D camera = new Camera2D();
        
        AnimatedSprite sprite1 = new AnimatedSprite("textures/player.png", 1, 7);
        camera.addChild(sprite1);
    
        Sprite sprite2 = new Sprite("textures/test_texture.png");

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
        source.setReferenceDistance(5000);
        source.setMaxDistance(7500);
        source.play(clip);
    
        window.showCursor(false);
        window.setVisible(true);
        
        int timer = 0, maxTime = 10, frameIndex = 0;
        Vector3 horSpeed = new Vector3(20, 0, 0), verSpeed = new Vector3(0, 20, 0);
        while (!window.isCloseRequested())
        {
            if (timer >= maxTime)
            {
                timer = 0;
                sprite1.setFrame((frameIndex < 6) ? frameIndex++ : (frameIndex = 0));
            }

            window.clear();
    
            camera.update();
    
            sprite2.render();
            camera.render();

            window.update();
            timer++;
            
            if (Keyboard.getKey(KeyCode.Up))
            {
                camera.transform.move(verSpeed.negate());
            }
            if (Keyboard.getKey(KeyCode.Down))
            {
                camera.transform.move(verSpeed);
            }

            if (Keyboard.getKey(KeyCode.Left))
            {
                camera.transform.move(horSpeed.negate());
            }
            if (Keyboard.getKey(KeyCode.Right))
            {
                camera.transform.move(horSpeed);
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
