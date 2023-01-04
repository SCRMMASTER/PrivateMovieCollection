package DAL;

import BE.Movie;
import DAL.Interface;

import java.util.List;

public interface IMovieDataAccess {
    public List<Movie> getAllMovie() throws Exception;
    public Movie createMovie(String movieTitle, Double imdbrating, int personalrating, String filepath, Double lastviewed, int year) throws Exception;
    public Movie deleteMovies(Movie movie) throws Exception;
}
