package saci.backend.playlist;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import saci.backend.song.Song;

import java.util.List;

/**
 * Created by Corina on 5/20/2017.
 */
@Document
public class Playlist {

    @Id
    private String id;
    private String userId;
    private String name;
    private List<Song> songs;

    /**
     * The ids of the users that are following this playlist.
     */
    private List<String> following;

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

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }
}
