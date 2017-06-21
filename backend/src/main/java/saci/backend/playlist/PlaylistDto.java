package saci.backend.playlist;

import saci.backend.song.SongDto;

import java.util.List;

/**
 * @author Stănilă Ioan, 5/27/2017.
 */
public class PlaylistDto {

    private String id;
    private String userId;
    private String name;
    private List<SongDto> songs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }
}
