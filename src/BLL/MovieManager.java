package BLL;

import BE.Movie;
import DAL.IMovieDataAccess;
import DAL.db.MovieDAO_DB;
import java.time.LocalDate;
import java.util.List;

public class MovieManager {

    private IMovieDataAccess movieDAO_DB;

    public MovieManager(){movieDAO_DB = new MovieDAO_DB();}
    //Get all movies
    public List<Movie> getAllMovies() throws Exception {
        return movieDAO_DB.getAllMovie();
    }

    /**
     * Creating a movie with the parameters
     * @param movieTitle
     * @param imdbRating
     * @param personalrating
     * @param filepath
     * @param lastviewed
     * @param year
     * @return
     * @throws Exception
     */
    public Movie createMovie(String movieTitle, Double imdbRating, int personalrating, String filepath, LocalDate lastviewed, int year) throws Exception {
        return movieDAO_DB.createMovie(movieTitle,imdbRating,personalrating,filepath,lastviewed,year);
    }
    //Deleting a movie
    public void deleteMovie(Movie movie) throws Exception{
        movieDAO_DB.deleteMovies(movie);
    }
    //Adding personalrating to a movie
    public void personalRating(Movie updatedMovie) throws Exception {
        movieDAO_DB.personalRating(updatedMovie);
    }
    //Adding lastview to a movie
    public void editLastview(Movie updatedDate) throws Exception{
        movieDAO_DB.editLastview(updatedDate);
    }

}
