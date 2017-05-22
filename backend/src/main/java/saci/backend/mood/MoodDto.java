package saci.backend.mood;

/**
 * @author Stănilă Ioan, 5/22/2017.
 */
public class MoodDto {

    private String color;
    private String mood;

    public MoodDto() {
    }

    public MoodDto(String color, String mood) {
        this.color = color;
        this.mood = mood;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
