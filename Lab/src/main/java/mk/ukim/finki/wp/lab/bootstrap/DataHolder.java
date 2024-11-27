package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists;
    public static List<Song> songs;

    @PostConstruct
    public void init(){

        songs = new ArrayList<>();

        songs.add(new Song("1", "Song 1", "Pop", 2020, new ArrayList<>()));
        songs.add(new Song("2", "Song 2", "Rock", 2018, new ArrayList<>()));
        songs.add(new Song("3", "Song 3", "Jazz", 2019, new ArrayList<>()));
        songs.add(new Song("4", "Song 4", "Classical", 2021, new ArrayList<>()));
        songs.add(new Song("5", "Song 5", "Hip-Hop", 2022, new ArrayList<>()));

       artists = new ArrayList<>();

        artists.add(new Artist(1L, "John", "Doe", "rock"));
        artists.add(new Artist(2L, "Jane", "Smith", "singer"));
        artists.add(new Artist(3L, "Alice", "Johnson", "rock"));
        artists.add(new Artist(4L, "Bob", "Brown", "jazz"));
        artists.add(new Artist(5L, "Charlie", "Davis", "punk"));
    }
}
