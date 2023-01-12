package GUI.Model;

import BE.Movie;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class MovieModel {
    private ObservableList<Movie> moviesToBeViewed;
    private MovieManager movieManager;
    public Movie createdMovie;
    public Movie selectedMovie;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }

    public void searchMovie(String query) throws Exception {
        List<Movie> searchResults = movieManager.searchMovies(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(searchResults);
    }

    public ObservableList<Movie> getObservableMovies() {
        return moviesToBeViewed;
    }

    public void createMovie(String movieTitle, Double imdbrating, int personalrating, String filepath, LocalDate lastviewed, int year) throws Exception {
        createdMovie = movieManager.createMovie(movieTitle, imdbrating, personalrating, filepath, lastviewed, year);
        moviesToBeViewed.add(createdMovie);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }

    public void deleteMovie(Movie movie) throws Exception {
        movieManager.deleteMovie(movie);
        moviesToBeViewed.remove(movie);
    }

    public void personalRating(Movie updatedMovie) throws Exception {
        movieManager.personalRating(updatedMovie);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }



}
