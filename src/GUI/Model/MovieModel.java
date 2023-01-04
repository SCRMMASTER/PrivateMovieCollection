package GUI.Model;

import BE.Movie;
import BLL.MovieManager;

public class MovieModel {

    private MovieManager movieManager;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
    }

    public void createMovie(String movieTitle, Double imdbrating, int personalrating, String filepath, Double lastviewed, int year) throws Exception {
        Movie m = movieManager.createMovie(movieTitle, imdbrating, personalrating, filepath,lastviewed,year);

    }
    public void deleteMovie(Movie movie) throws Exception{
        movieManager.deleteMovie(movie);
    }
}
