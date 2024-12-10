package mk.ukim.finki.wp.lab.model;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;



@Data
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;
    private Long id;
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.id = (long) (Math.random() * 1000);
        this.album = album;
        this.performers = new ArrayList<>();
    }

    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;
        this.id = (long) (Math.random() * 1000);
        this.album = album;
    }

}