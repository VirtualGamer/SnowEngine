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

import java.util.*;

public final class MusicPlayer
{
    private Map<Integer, AudioClip> m_TrackList;
    private AudioSource m_TrackPlayer;
    private ArrayList<Integer> m_Playlist;
    private int m_PlaylistOffset;
    
    public MusicPlayer()
    {
        m_TrackList = new HashMap<>();
        m_TrackList.put(0, AudioMaster.loadAudioClip("music/POL-unbeatable-guild-short.wav"));
        m_TrackPlayer = new AudioSource();
        m_Playlist = new ArrayList<>();
        m_PlaylistOffset = 0;
    }
    
    private void shufflePlaylist()
    {
    
        int lastTrack = -1;
        if (!m_Playlist.isEmpty())
        {
            lastTrack = m_Playlist.get(m_Playlist.size() - 1);
            m_Playlist.clear();
        }
        
        for (int i = 0; i < m_TrackList.size(); i++)
        {
            m_Playlist.add(i);
        }
        
        if (m_TrackList.size() > 1)
        {
            this.swapList();
    
            while (lastTrack != -1 && m_Playlist.get(0) == lastTrack)
            {
                this.swapList();
            }
        }
    }
    
    private void swapList()
    {
        for (int i = 0; i < m_Playlist.size(); i++)
        {
            Random random = new Random();
            int trackId = random.nextInt(m_Playlist.size() - 1) + 1;
            Collections.swap(m_Playlist, i, trackId);
        }
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
    
    public void playNextSong()
    {
        m_TrackPlayer.play(this.next());
    }
    
    private AudioClip next()
    {
        if (m_PlaylistOffset >= m_Playlist.size())
        {
            this.shufflePlaylist();
            m_PlaylistOffset = 0;
        }
        return m_TrackList.get(m_Playlist.get(m_PlaylistOffset++));
    }
    
    public boolean isPlayingMusic()
    {
        return m_TrackPlayer.isPlaying();
    }
}
