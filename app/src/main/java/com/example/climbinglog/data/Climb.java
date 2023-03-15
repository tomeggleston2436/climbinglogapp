package com.example.climbinglog.data;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "climbs_table")
public class Climb implements Comparable<Climb> {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String date;
    private String difficulty;
    private String notes;

    public Climb(String name, String date, String difficulty, String notes) {
        this.name = name;
        this.date = date;
        this.difficulty = difficulty;
        this.notes = notes;


    }

    // Getters and setters for the Climb class

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getDifficulty() {

        return difficulty;
    }

    public void setDifficulty(String difficulty) {

        this.difficulty = difficulty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {

        this.notes = notes;
    }

    @Override
    public int compareTo(Climb o) {
        return o.getDate().compareTo(this.getDate());
    }
}
