package DAL;

import BE.Movie;

import java.time.LocalDate;
import java.util.List;

public interface IMovieDataAccess {
    public List<Movie> getAllMovie() throws Exception;
    //public List<Movie> getAllFilePaths() throws Exception;
    public Movie createMovie(String movieTitle, Double imdbrating, int personalrating, String filepath, LocalDate lastviewed, int year) throws Exception;
    public Movie deleteMovies(Movie movie) throws Exception;
    public Movie getFilePath(Movie selectedMovie) throws Exception;
    public void personalRating(Movie movie) throws Exception;


}
