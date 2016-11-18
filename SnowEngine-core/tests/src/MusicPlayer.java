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

import com.snowengine.audio.AudioClip;
import com.snowengine.audio.AudioMaster;
import com.snowengine.audio.AudioSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class MusicPlayer
{
    private Map<Integer, AudioClip> m_TrackList;
    private AudioSource m_TrackPlayer;
    
    public MusicPlayer()
    {
        m_TrackList = new HashMap<>();
        m_TrackList.put(0, AudioMaster.loadAudioClip("music/all-or-nothing-raiden.wav"));
        m_TrackList.put(1, AudioMaster.loadAudioClip("music/flap-toward-the-hope-raiden.wav"));
        m_TrackList.put(2, AudioMaster.loadAudioClip("music/dawn-of-sorrow-raiden.wav"));
        m_TrackList.put(3, AudioMaster.loadAudioClip("music/advantageous-development-raiden.wav"));
        m_TrackList.put(4, AudioMaster.loadAudioClip("music/can-t-retrace-raiden.wav"));
        m_TrackList.put(5, AudioMaster.loadAudioClip("music/a-stormy-front-raiden.wav"));
        m_TrackPlayer = new AudioSource();
    }
    
    public void delete()
    {
        m_TrackList.values().forEach(AudioClip::delete);
        m_TrackPlayer.delete();
    }
    
    public void stop()
    {
        m_TrackPlayer.stop();
    }
    
    public void shuffle()
    {
        Random random = new Random();
        AudioClip track = m_TrackList.get(random.nextInt(6));
        m_TrackPlayer.play(track);
    }
    
    public boolean isPlayingMusic()
    {
        return m_TrackPlayer.isPlaying();
    }
}
