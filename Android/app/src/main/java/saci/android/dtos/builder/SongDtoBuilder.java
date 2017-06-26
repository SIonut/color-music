package saci.android.dtos.builder;

import java.util.List;

import saci.android.dtos.SongDto;

/**
 * Created by corina on 22.06.2017.
 */
public class SongDtoBuilder {

    private SongDto song;

    private String id;
    private String link;
    private String title;
    private String author;
    private List<String> colors;

    public SongDtoBuilder() {
        song = new SongDto();
    }

    public SongDtoBuilder setId(String id) {
        song.setId(id);
        return this;
    }

    public SongDtoBuilder setLink(String link) {
        song.setLink(link);
        return this;
    }

    public SongDtoBuilder setTitle(String title) {
        song.setTitle(title);
        return this;
    }

    public SongDtoBuilder setAuthor(String author) {
        song.setAuthor(author);
        return this;
    }

    public SongDtoBuilder setColor(List<String> colors) {
//        JSONArray colorsList = colors.getJSONArray();
//        for
//        song.setColor(color);
        return this;
    }

    public SongDto build() {
        return song;
    }
}
