package DAL;

import BE.Movie;

import java.time.LocalDate;
import java.util.List;

public interface IMovieDataAccess {
    List<Movie> getAllMovie() throws Exception;
    Movie createMovie(String movieTitle, Double imdbrating, int personalrating, String filepath, LocalDate lastviewed, int year) throws Exception;
    Movie deleteMovies(Movie movie) throws Exception;
    //Movie getFilePath(Movie selectedMovie) throws Exception;
     void personalRating(Movie movie) throws Exception;
    void editLastview(Movie updatedDate) throws Exception;


}
