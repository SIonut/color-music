package saci.android.dtos.builder;

import java.util.List;

import saci.android.dtos.Song;

/**
 * Created by corina on 22.06.2017.
 */

public class SongBuilder {

    private Song song;

    private String id;
    private String link;
    private String title;
    private String author;
    private List<String> color;

    public SongBuilder() {
        song = new Song();
    }

    public SongBuilder setId(String id) {
        song.setId(id);
        return this;
    }

    public SongBuilder setLink(String link) {
        song.setLink(link);
        return this;
    }

    public SongBuilder setTitle(String title) {
        song.setTitle(title);
        return this;
    }

    public SongBuilder setAuthor(String author) {
        song.setAuthor(author);
        return this;
    }

    public SongBuilder setColor(List<String> color) {
        song.setColor(color);
        return this;
    }

    public Song build() {
        return song;
    }
}
