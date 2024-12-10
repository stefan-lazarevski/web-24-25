package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songs")
public class SongController {

    AlbumService albumService;
    SongService songService;

    public SongController(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("songs", songService.listSongs());
        model.addAttribute("error", error);
        return "listSongs";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           @RequestParam Long albumId) {

        try {
            songService.addNewSong(title, trackId, genre, Integer.parseInt(releaseYear),
                    albumService.findById(albumId).orElse(null));
        }
        catch (Exception e) {
            return "redirect:/add";
        }
        return "redirect:/songs";
    }

    @PostMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           @RequestParam Long albumId) {

        try {
            songService.editSong(songId, title, trackId, genre, Integer.parseInt(releaseYear),
                    albumService.findById(albumId).orElse(null));
        }
        catch (Exception e){
            return "redirect:/add-song";
        }
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("song", songService.findBySongId(id));
        model.addAttribute("songId", id);
        return "add-song";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", albumService.findAll());
        return "add-song";
    }

    @GetMapping("/delete/{id}") //button so href dodadeno zato ne e @Delete
    public String deleteSong(@PathVariable Long id) {
        try {
            songService.deleteById(id);
        }
        catch (Exception e) {
            return "redirect:/add-form";
        }
        return "redirect:/songs";
    }

}
