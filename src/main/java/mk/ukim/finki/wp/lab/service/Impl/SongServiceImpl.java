package mk.ukim.finki.wp.lab.service.Impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.model.Exceptions.SongDoesntExistException;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public void addArtistToSong(Artist artist, Song song) {
        List<Artist> artists = song.getArtists();
        artists.add(artist);
        song.setArtists(artists);
        songRepository.save(song);
    }

    @Override
    public Optional<Song> findByTrackId(String trackId) {
        return Optional.ofNullable(songRepository.findByTrackId(trackId));
    }

    public Song findBySongId(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteSongById(Long id) {
        if(id == null) {
            throw new SongDoesntExistException();
        }
        songRepository.deleteById(id);
    }

    @Override
    public void addNewSong(String title, String trackId, String genre, int releaseYear, Album album) {
        if(title == null || title.isEmpty()
                || trackId == null || trackId.isEmpty()
                || genre == null || genre.isEmpty() || album == null) {
            throw new InvalidArgumentsException();
        }

        songRepository.save(new Song(trackId, title, genre, releaseYear, album));
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
        songRepository.save(editedSong);
    }

}
