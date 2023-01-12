/*
 * Created by
 * Mathias, Kasper, Magnus and Jesper
 */
package BE;

import java.util.ArrayList;
import java.util.List;

public class Category {

    int Id;

    String Genre;

    public List<Movie> movies = new ArrayList<>();

    public Category(int Id, String Genre){
        this.Id = Id;
        this.Genre = Genre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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
