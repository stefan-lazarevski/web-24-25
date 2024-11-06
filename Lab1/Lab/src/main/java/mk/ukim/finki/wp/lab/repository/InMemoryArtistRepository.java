package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtistRepository {
    List<Artist> artists = new ArrayList<>();


    public InMemoryArtistRepository() {
        artists.add(new Artist(1L, "John", "Doe", "rock"));
        artists.add(new Artist(2L, "Jane", "Smith", "singer"));
        artists.add(new Artist(3L, "Alice", "Johnson", "rock"));
        artists.add(new Artist(4L, "Bob", "Brown", "jazz"));
        artists.add(new Artist(5L, "Charlie", "Davis", "punk"));
    }


    public List<Artist> findAll() {
        return artists;
    }

    public Optional<Artist> findById(Long id) {
        return artists.stream().filter(a->a.getId().equals(id)).findFirst();
    }

}
