package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Exceptions.SongDoesntExistException;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/artist-form")
public class ArtistController {
    ArtistService artistService;
    SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @PostMapping
    public String getAddArtistPage(@RequestParam String selectedSong, Model model) {
        model.addAttribute("artists", artistService.listArtists().stream()
                .filter(x->!songService.findByTrackId(selectedSong).orElseThrow(SongDoesntExistException::new).getArtists().contains(x)));
        model.addAttribute("songId", selectedSong);
        return "artistsList";
    }

    @PostMapping("/add/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String addArtist(@PathVariable String id,
                            @RequestParam Long artistId) {

        Song song = songService.findByTrackId(id).orElseThrow(SongDoesntExistException::new);
        Artist artist = artistService.findById(artistId).orElse(null);
        songService.addArtistToSong(artist, song);
        artistService.addSongToArtist(artist, song);
        return "redirect:/song-details/" + song.getId();
    }
}
