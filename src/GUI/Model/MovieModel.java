package GUI.Model;

import BE.Movie;
import BLL.MovieManager;
import BLL.unit.MovieSearcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.time.LocalDate;
import java.util.List;

public class MovieModel {
    private ObservableList<Movie> moviesToBeViewed;
    private ObservableList<Movie> allFilePaths;
    private FilteredList<Movie> filteredmovies;
    private MovieManager movieManager;
    public Movie createdMovie;
    public Movie selectedMovie;

    public MovieSearcher movieSearcher;

    /**
     * CRUD functions from the UI to MovieManager.
     */
    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
        filteredmovies = new FilteredList<>(moviesToBeViewed);
        allFilePaths = FXCollections.observableArrayList();
        movieSearcher = new MovieSearcher();
    }

    public void reloadAllMovies() throws Exception {
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }
    public void searchMovie(String query) throws Exception {
        movieSearcher.search(filteredmovies,query);
    }

    public FilteredList<Movie> getFilteredmovies() {
        return filteredmovies;
    }

    public void setMoviesToBeViewed(List<Movie> movies) {
       moviesToBeViewed.clear();
       moviesToBeViewed.addAll(movies);
    }

    public ObservableList<Movie> getObservableMovies() {
        return moviesToBeViewed;
    }

    public ObservableList<Movie> getAllFilePaths(){return allFilePaths;}

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

    public void editLastView(Movie updatedDate) throws Exception {
        movieManager.editLastview(updatedDate);
    }



}
