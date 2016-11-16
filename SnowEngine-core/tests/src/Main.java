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
    private static Matrix4 projection;
    
    public static void main(String args[])
    {
        FileUtils.setPathPrefix("./SnowEngine-core/assets/");
        Window window = new Window("SnowEngine!", 800, 600);
        window.setSizeCallback(Main::windowResize);
        window.setClearColor(new Vector3(0.25f, 0.5f, 0.75f));
        
        AnimatedSprite sprite1 = new AnimatedSprite("textures/wall.png", 2, 2);

        Shader shader = new Shader();
        shader.addVertexShader("shaders/basic.vert");
        shader.addFragmentShader("shaders/basic.frag");
        shader.compile();
        shader.enable();

        float halfWidth = window.getWidth() / 2.0f;
        float halfHeight = window.getHeight() / 2.0f;
        projection = Matrix4.orthographic(-halfWidth, halfWidth, halfHeight, -halfHeight, -1.0f, 1.0f);

        AudioClip clip = AudioMaster.loadAudioClip("audio/bounce.wav");
        AudioClip clip1 = AudioMaster.loadAudioClip("audio/music.wav");
        AudioClip clip2 = AudioMaster.loadAudioClip("audio/music2.wav");
        AudioClip clip3 = AudioMaster.loadAudioClip("audio/music3.wav");

        AudioMaster.setDistanceModel(DistanceModel.LinearDistanceClamped);
        AudioListener listener = new AudioListener();
        AudioSource source = new AudioSource();
        source.setReferenceDistance(5000);
        source.setMaxDistance(7500);
        source.play(clip);
    
        window.showCursor(false);
        window.setVisible(true);
        
        float x = 0, y = 0, speed = 20f;
        Vector3 rotSpeed = new Vector3(0, 0, 0);
        while (!window.isCloseRequested())
        {
            shader.setUniformMatrix4f("projection", projection);
            
            Vector3 position = new Vector3(x, y, 0);
            listener.setPosition(position);

            Matrix4 view = Matrix4.translate(position.negate());
            shader.setUniformMatrix4f("view", view);
            
            Matrix4 mvp = projection.copy();
            mvp.multiply(view.multiply(sprite1.transform.getTransformMatrix()));
            shader.setUniformMatrix4f("mvp", mvp);
            
            
            sprite1.update();

            window.clear();
            
            sprite1.transform.rotation.add(rotSpeed);
            sprite1.render();

            window.update();

            if (Keyboard.getKey(KeyCode.Up))
            {
                y -= speed;
            }
            if (Keyboard.getKey(KeyCode.Down))
            {
                y += speed;
            }

            if (Keyboard.getKey(KeyCode.Left))
            {
                x -= speed;
            }
            if (Keyboard.getKey(KeyCode.Right))
            {
                x += speed;
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
    
    private static void windowResize(int newWidth, int newHeight)
    {
        float halfWidth = newWidth / 2.0f;
        float halfHeight = newHeight / 2.0f;
        projection = Matrix4.orthographic(-halfWidth, halfWidth, halfHeight, -halfHeight, -1.0f, 1.0f);
    }
}
