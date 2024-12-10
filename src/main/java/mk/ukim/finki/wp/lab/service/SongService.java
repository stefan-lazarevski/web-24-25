package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService{
    List<Song> listSongs();
    void addArtistToSong(Artist artist, Song song);
    Optional<Song> findByTrackId(String trackId);
    void deleteById(Long id);
    void addNewSong(String title,
                    String trackId,
                    String genre,
                    int releaseYear,
                    Album album);

    void editSong(Long songId,
                  String title,
                  String trackId,
                  String genre,
                  int releaseYear,
                  Album album);

    Song findBySongId(Long id);
}
