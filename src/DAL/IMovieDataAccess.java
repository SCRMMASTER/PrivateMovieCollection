package DAL;

import BE.Movie;
import DAL.Interface;

import java.util.List;

public interface IMovieDataAccess {
    public List<Movie> getAllMovie() throws Exception;
    public Movie createMovie(String movieTitle, String category, Integer year, Double imdbrating, Integer personalrating, Double lastviewed, String filepath) throws Exception;
    public Movie deleteMovies(Movie movie) throws Exception;
}
