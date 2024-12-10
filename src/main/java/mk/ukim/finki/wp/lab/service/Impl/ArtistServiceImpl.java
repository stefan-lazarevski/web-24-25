package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final InMemoryArtistRepository artistRepository;

    public ArtistServiceImpl(InMemoryArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @Override
    public List<Artist> listArtists() {
        return this.artistRepository.findAll();
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return this.artistRepository.findById(id);
    }
}
