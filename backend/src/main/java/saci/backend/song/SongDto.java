package saci.backend.song;

import java.util.List;

/**
 * @author Stănilă Ioan, 5/27/2017.
 */
public class SongDto {

    private String id;
    private String link;
    private List<String> color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
