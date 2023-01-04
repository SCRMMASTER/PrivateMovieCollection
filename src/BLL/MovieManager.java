package BLL;

import BE.Movie;
import DAL.IMovieDataAccess;
import DAL.db.MovieDAO_DB;

public class MovieManager {

    private IMovieDataAccess movieDAO_DB;

    public MovieManager(){movieDAO_DB = new MovieDAO_DB();}
    public Movie createMovie(String movieTitle, Double imdbrating, int personalrating, String filepath, Double lastviewed, int year) throws Exception {
        return movieDAO_DB.createMovie(movieTitle,imdbrating,personalrating,filepath,lastviewed,year);
    }
    public void deleteMovie(Movie movie) throws Exception{
        movieDAO_DB.deleteMovies(movie);
    }

}
