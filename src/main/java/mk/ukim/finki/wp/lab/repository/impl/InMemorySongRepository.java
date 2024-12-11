package mk.ukim.finki.wp.lab.repository.impl;

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

    public Song findBySongId(Long id) {
        return DataHolder.songs.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        Optional<Song> songOptional = this.findByTrackId(song.getTrackId());
        if (songOptional.isEmpty())
        {
            throw new SongDoesntExistException();
        }
        songOptional
                .get()
                .getArtists()
                .add(artist);

        return artist;
    }

    public int findIndexById(Long id) {
        for (int i = 0; i < DataHolder.songs.size(); i++) {
            if (DataHolder.songs.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteSong(Long id) {
        DataHolder.songs.removeIf(x->x.getId().equals(id));
    }

    public void saveSong(Song song) {
        int idx = findIndexById(song.getId());
        if(idx == -1) {
            DataHolder.songs.add(song);
        }
        else {
            DataHolder.songs.set(idx, song);
        }
    }
}
