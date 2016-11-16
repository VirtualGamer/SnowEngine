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
import com.snowengine.utils.*;

public final class Main
{
    public static void main(String args[])
    {
        FileUtils.setPathPrefix("./SnowEngine-core/assets/");
        Window window = new Window("SnowEngine!", 960, 540);
        window.setClearColor(new Vector3(0.25f, 0.5f, 0.75f));
        window.setVisible(true);

        float vertices[] =
        {
            -0.5f,-0.5f, 0.0f,
            -0.5f, 0.5f, 0.0f,
             0.5f, 0.5f, 0.0f,
             0.5f,-0.5f, 0.0f
        };

        float colorsA[] =
        {
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        };

        float colorsB[] =
        {
            0.2f, 0.3f, 0.8f, 1.0f,
            0.3f, 0.8f, 0.2f, 1.0f,
            0.8f, 0.2f, 0.3f, 1.0f,
            0.2f, 0.8f, 0.3f, 1.0f
        };

        short indices[] =
        {
            0, 1, 2,
            2, 3, 0
        };

        Mesh sprite1 = new Mesh(), sprite2 = new Mesh();
        sprite1.setMeshData(vertices, colorsA, indices);
        sprite2.setMeshData(vertices, colorsB, indices);

        Shader shader = new Shader();
        shader.addVertexShader("shaders/basic.vert");
        shader.addFragmentShader("shaders/basic.frag");
        shader.compile();
        shader.enable();

        Matrix4 projection = Matrix4.orthographic(-4.0f, 4.0f, 3.0f, -3.0f, -1.0f, 1.0f);
        shader.setUniformMatrix4f("projection", projection);

        AudioClip clip = AudioMaster.loadAudioClip("audio/bounce.wav");
        AudioClip clip1 = AudioMaster.loadAudioClip("audio/music.wav");
        AudioClip clip2 = AudioMaster.loadAudioClip("audio/music2.wav");
        AudioClip clip3 = AudioMaster.loadAudioClip("audio/music3.wav");

        AudioMaster.setDistanceModel(DistanceModel.LinearDistanceClamped);
        AudioListener listener = new AudioListener();
        AudioSource source = new AudioSource();
        source.setReferenceDistance(6);
        source.setMaxDistance(10);
        source.play(clip);

        float x = 0, y = 0, speed = 0.2f;
        float rot1 = 0, rot2 = 0, rotSpeed1 = 0.3f, rotSpeed2 = 0.6f;
        while (!window.isCloseRequested())
        {
            Vector3 position = new Vector3(x, y, 0);
            listener.setPosition(position);

            Matrix4 view = Matrix4.translate(position.negate());
            shader.setUniformMatrix4f("view", view);

            window.clear();

            // Draw sprite 1
            Matrix4 model = Matrix4.translate(new Vector3(-1, -1, 0));
            model.multiply(Matrix4.rotate(rot1, new Vector3(0, 0, 1)));

            Matrix4 mvp = projection.copy();
            mvp.multiply(view).multiply(model);
            shader.setUniformMatrix4f("mvp", mvp);
            shader.setUniformMatrix4f("model", model);
            sprite1.bind();
            sprite1.draw();
            sprite1.unbind();

            // Draw sprite 2
            model = Matrix4.translate(new Vector3(1, 1, 0));
            model.multiply(Matrix4.rotate(-rot2, new Vector3(0, 0, 1)));

            mvp = projection.copy();
            mvp.multiply(view).multiply(model);
            shader.setUniformMatrix4f("mvp", mvp);
            sprite2.bind();
            sprite2.draw();
            sprite2.unbind();

            window.update();

            rot1 += rotSpeed1;
            rot2 += rotSpeed2;

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
}
