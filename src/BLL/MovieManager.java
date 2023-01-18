package BLL;

import BE.Movie;
import DAL.IMovieDataAccess;
import DAL.db.MovieDAO_DB;

import java.time.LocalDate;
import java.util.List;

public class MovieManager {

    private IMovieDataAccess movieDAO_DB;

    public MovieManager(){movieDAO_DB = new MovieDAO_DB();}

    public List<Movie> getAllMovies() throws Exception {
        return movieDAO_DB.getAllMovie();
    }

    public Movie createMovie(String movieTitle, Double imdbRating, int personalrating, String filepath, LocalDate lastviewed, int year) throws Exception {
        return movieDAO_DB.createMovie(movieTitle,imdbRating,personalrating,filepath,lastviewed,year);
    }
    public void deleteMovie(Movie movie) throws Exception{
        movieDAO_DB.deleteMovies(movie);
    }

    public void personalRating(Movie updatedMovie) throws Exception {
        movieDAO_DB.personalRating(updatedMovie);
    }

    public void editLastview(Movie updatedDate) throws Exception{
        movieDAO_DB.editLastview(updatedDate);
    }

}
