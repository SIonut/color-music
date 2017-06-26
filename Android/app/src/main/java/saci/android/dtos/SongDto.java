package saci.android.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Corina on 5/20/2017.
 */
public class SongDto implements Serializable{

    private String id;
    private String link;
    private String title;
    private String author;
    private List<String> color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
