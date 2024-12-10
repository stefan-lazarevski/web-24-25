package mk.ukim.finki.wp.lab.model.Exceptions;

public class SongDoesntExistException extends RuntimeException{
    public SongDoesntExistException() {
        super("Artist dose not exist");
    }
}
