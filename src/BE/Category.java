/*
 * Created by
 * Mathias, Kasper, Magnus and Jesper
 */
package BE;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int Id;
    private String Genre;
    public List<Movie> movies;

    /**
     * Constructor for the category object.
     * @param Id
     * @param Genre
     */
    public Category(int Id, String Genre){
        this.Id = Id;
        this.Genre = Genre;
        this.movies = new ArrayList<>();
    }

    /**
     * Getters and setters.
     */
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String toString(){
        return Genre;
    }

    public List<Movie> getMovie(){
        return movies;
    }

    public void setMovie(List<Movie> movies) {
        this.movies = movies;
    }
}
