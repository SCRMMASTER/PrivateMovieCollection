package BLL;

import BE.Movie;
import BLL.unit.MovieSearcher;
import DAL.IMovieDataAccess;
import DAL.db.MovieDAO_DB;

import java.util.List;

public class MovieManager {

    private IMovieDataAccess movieDAO_DB;

    private MovieSearcher movieSearcher = new MovieSearcher();

    public MovieManager(){movieDAO_DB = new MovieDAO_DB();}

    public List<Movie> getAllMovies() throws Exception {
        return movieDAO_DB.getAllMovie();
    }

    public List<Movie> searchMovies(String query) throws Exception {
        List<Movie> allMovies = getAllMovies();
        List<Movie> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }

    public Movie createMovie(String movieTitle, Double imdbRating, int personalrating, String filepath, Double lastviewed, int year) throws Exception {
        return movieDAO_DB.createMovie(movieTitle,imdbRating,personalrating,filepath,lastviewed,year);
    }
    public void deleteMovie(Movie movie) throws Exception{
        movieDAO_DB.deleteMovies(movie);
    }

}
