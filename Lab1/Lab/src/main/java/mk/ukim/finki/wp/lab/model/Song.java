package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Data
public class Song {
    String trackId;
    String title;
    String genre;
    int releaseYear;
    List<Artist> performers;
}
