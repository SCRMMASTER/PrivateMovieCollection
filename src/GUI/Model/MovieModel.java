package GUI.Model;

import BE.Movie;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MovieModel extends BaseModel {
    private ObservableList<Movie> moviesToBeViewed;

    private MovieManager movieManager;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }


    public void searchMovie(String query) throws Exception {
        //List<Movie> searchResults = movieManager.searchMovies(query);
       // moviesToBeViewed.clear();
       // moviesToBeViewed.addAll(searchResults);
    }


    public ObservableList<Movie> getObservableMovies(){
        return moviesToBeViewed;
    }

    public void createMovie(String movieTitle, Double imdbrating, int personalrating, String filepath, Double lastviewed, int year) throws Exception {
        Movie m = movieManager.createMovie(movieTitle, imdbrating, personalrating, filepath,lastviewed,year);
        moviesToBeViewed.add(m);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(movieManager.getAllMovies());

    }
    public void deleteMovie(Movie movie) throws Exception{
        movieManager.deleteMovie(movie);
        moviesToBeViewed.remove(movie);
    }
}
