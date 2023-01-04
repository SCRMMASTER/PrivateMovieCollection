package BLL.unit;

import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher {

    public List<Movie> search(List<Movie> searchBase, String query){
        List<Movie> searchResult = new ArrayList<>();

        for (Movie movie : searchBase){
            if (compareToMovieTitle(query, movie)|| compareToMovieCategory(query, movie))
            {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    private boolean compareToMovieTitle(String query, Movie movie){
        return movie.getMovieTitle().toLowerCase().contains(query.toLowerCase());
    }
    private boolean compareToMovieCategory(String query, Movie movie){
        return movie.getCategory().toLowerCase().contains(query.toLowerCase());
    }
}
