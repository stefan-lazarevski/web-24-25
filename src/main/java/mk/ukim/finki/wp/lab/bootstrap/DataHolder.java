package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists;
    public static List<Song> songs;
    public static List<Album> albums;

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    //private final ArtistService artistService;

    public DataHolder(AlbumRepository albumRepository, ArtistRepository artistRepository, SongRepository songRepository, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        //this.artistService = artistService;
    }

    @PostConstruct
    public void init(){


       artists = new ArrayList<>();

        artists.add(new Artist("John", "Doe", "rock"));
        artists.add(new Artist("Jane", "Smith", "singer"));
        artists.add(new Artist("Alice", "Johnson", "rock"));
        artists.add(new Artist("Bob", "Brown", "jazz"));
        artists.add(new Artist("Charlie", "Davis", "punk"));
        artistRepository.saveAll(artists);

        albums = new  ArrayList<>();
        albums.add(new Album("Album 1", "RNB", "2024"));
        albums.add(new Album("Album 2", "Rap", "2019"));
        albums.add(new Album("Album 3", "Pop", "2014"));
        albums.add(new Album("Album 4", "Metal", "2014"));
        albums.add(new Album("Album 5", "Jazz", "2017"));
        albumRepository.saveAll(albums);

        songs = new ArrayList<>();
        songs.add(new Song("1", "Song 1", "Pop", 2020, artists.subList(0, 1), albums.get(0)));
        songs.add(new Song("2", "Song 2", "Rock", 2018, artists.subList(1, 2), albums.get(1)));
        songs.add(new Song("3", "Song 3", "Jazz", 2019, artists.subList(2, 3), albums.get(2)));
        songs.add(new Song("4", "Song 4", "Classical", 2021, artists.subList(3, 4), albums.get(3)));
        songs.add(new Song("5", "Song 5", "Hip-Hop", 2022, artists.subList(4, 5), albums.get(4)));
        songRepository.saveAll(songs);

//        artistService.addSongToArtist(artists.get(0), songs.get(0));
//        artistService.addSongToArtist(artists.get(1), songs.get(1));
//        artistService.addSongToArtist(artists.get(2), songs.get(2));
//        artistService.addSongToArtist(artists.get(3), songs.get(3));
//        artistService.addSongToArtist(artists.get(4), songs.get(4));
    }
}
