package BLL.unit;

import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher {

    public List<Movie> search(List<Movie> searchBase, String query) {
        List<Movie> searchResult = new ArrayList<>();

        for (Movie movie : searchBase) {
            if (compareToMovieTitle(query, movie) || (compareToIMDBRating(query,movie))||(compareToYear(query,movie)))
            {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    private boolean compareToMovieTitle(String query, Movie movie) {
        return movie.getMovieTitle().toLowerCase().contains(query.toLowerCase());
    }
    private boolean compareToIMDBRating(String query, Movie movie) {
        return Boolean.parseBoolean(String.valueOf(Double.toString(movie.getImdbRating()).contains(query)));
    }
    private boolean compareToYear(String query, Movie movie){
        return Boolean.parseBoolean(String.valueOf(Integer.toString(movie.getYear()).contains(query)));
    }






    /*
    private boolean compareToIMDBRating(String toString, Movie movie) {
       // Double.toString(movie.getImdbRating());
        return (Double.toString(movie.getImdbRating()));
    }

     */








    /*
    private boolean compareToIMDBRating(String query, Movie movie) {
        double rating = Double.parseDouble(query);
        while (rating < movie.getImdbRating() && rating < 10) {
            rating = rating + 0.1;
        }
        return rating > Double.parseDouble(query);
    }

 */







}
