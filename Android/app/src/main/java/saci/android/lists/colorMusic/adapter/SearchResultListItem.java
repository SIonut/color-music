package saci.android.lists.colorMusic.adapter;

/**
 * Created by Corina on 5/26/2017.
 */

public class SearchResultListItem {

    private String songTitle;
    private String songAuthor;
    private String playlist;
    private String user;

    public SearchResultListItem() {
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
