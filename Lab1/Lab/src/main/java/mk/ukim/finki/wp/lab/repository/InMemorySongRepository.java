package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Exceptions.SongDoesntExistException;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySongRepository {

    List<Song> songs = new ArrayList<>();

    public InMemorySongRepository() {
        songs.add(new Song("1", "Song 1", "Pop", 2020, new ArrayList<>()));
        songs.add(new Song("2", "Song 2", "Rock", 2018, new ArrayList<>()));
        songs.add(new Song("3", "Song 3", "Jazz", 2019, new ArrayList<>()));
        songs.add(new Song("4", "Song 4", "Classical", 2021, new ArrayList<>()));
        songs.add(new Song("5", "Song 5", "Hip-Hop", 2022, new ArrayList<>()));
    }


    public List<Song> findAll() {
        return songs;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return songs.stream().filter(s->s.getTrackId().equals(trackId)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        Optional<Song> songOptional = this.findByTrackId(song.getTrackId());
        if (songOptional.isEmpty())
        {
            throw new SongDoesntExistException();
        }
        songOptional
                .get()
                .getPerformers()
                .add(artist);

        return artist;
    }
}
