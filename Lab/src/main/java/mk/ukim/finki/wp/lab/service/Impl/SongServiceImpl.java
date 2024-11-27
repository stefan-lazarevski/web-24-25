package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Exceptions.SongDoesntExistException;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.InMemorySongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final InMemorySongRepository songRepository;

    public SongServiceImpl(InMemorySongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        if (song.getPerformers().contains(artist))
        {
            return artist;
        }

        try
        {
            this.songRepository.addArtistToSong(artist, song);
        } catch (SongDoesntExistException e)
        {
            return null;
        }

        return artist;
    }


    @Override
    public Optional<Song> findByTrackId(String trackId) {
        return this.songRepository.findByTrackId(trackId);
    }
}
