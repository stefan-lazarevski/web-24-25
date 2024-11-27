package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Exceptions.SongDoesntExistException;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySongRepository {


    public List<Song> findAll() {
        return DataHolder.songs;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return DataHolder.songs.stream().filter(s->s.getTrackId().equals(trackId)).findFirst();
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
