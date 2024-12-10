package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Exceptions.InvalidArgumentsException;
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

//    @Override
//    public Artist addArtistToSong(Artist artist, Song song) {
//        if (song.getPerformers().contains(artist))
//        {
//            return artist;
//        }
//
//        try
//        {
//            this.songRepository.addArtistToSong(artist, song);
//        } catch (SongDoesntExistException e)
//        {
//            return null;
//        }
//
//        return artist;
//    }

    @Override
    public void addArtistToSong(Artist artist, Song song) { //Zoshto vrakja Artist?
        songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Optional<Song> findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    public Song findBySongId(Long id) {
        return songRepository.findBySongId(id);
    }

    @Override
    public void deleteById(Long id) {
        if(id == null) {
            throw new SongDoesntExistException();
        }
        songRepository.deleteSong(id);
    }

    @Override
    public void addNewSong(String title, String trackId, String genre, int releaseYear, Album album) {
        if(title == null || title.isEmpty()
                || trackId == null || trackId.isEmpty()
                || genre == null || genre.isEmpty() || album == null) {
            throw new InvalidArgumentsException();
        }

        songRepository.saveSong(new Song(trackId, title, genre, releaseYear, album));
    }

    @Override
    public void editSong(Long songId, String title, String trackId, String genre, int releaseYear, Album album) {
        if(songId == null
                || title == null || title.isEmpty()
                || trackId == null || trackId.isEmpty()
                || genre == null || genre.isEmpty() || album == null) {
            throw new InvalidArgumentsException();
        }

        Song editedSong = findBySongId(songId);
        editedSong.setTitle(title);
        editedSong.setTrackId(trackId);
        editedSong.setGenre(genre);
        editedSong.setReleaseYear(releaseYear);
        editedSong.setAlbum(album);
        songRepository.saveSong(editedSong);
    }

}
