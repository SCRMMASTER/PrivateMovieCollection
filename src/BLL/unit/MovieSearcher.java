package BLL.unit;

import BE.Category;
import BE.Movie;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MovieSearcher {
    private Category selectedCategory;


    public void search(FilteredList<Movie> searchBase, String query) {
      //  ObservableList<Movie> searchResult = new ArrayList<>();
       /* for (Movie movie : searchBase) {
            if (selectedCategory != null)
                if (compareToMovieTitle(query, movie) || (compareToIMDBRating(query, movie)) || (compareToYear(query, movie))) {
                    searchResult.add(movie);
                }
            if (selectedCategory == null)
                if (compareToMovieTitle(query, movie) || (compareToIMDBRating(query, movie)) || (compareToYear(query, movie))) {
                    searchResult.add(movie);
                }
        }
        return searchResult;

        */

        searchBase.predicateProperty().bind(Bindings.createObjectBinding(()->
                movie -> movie.getMovieTitle().toLowerCase().contains(query.toLowerCase())||
                        Integer.toString(movie.getYear()).contains(query)||
                        Double.toString(movie.getImdbRating()).contains(query)
        ));
       // searchBase.setPredicate(movie ->
         //   movie.getMovieTitle().toLowerCase().contains(query.toLowerCase()));
         //  searchBase.setPredicate(movie -> Integer.toString(movie.getYear()).contains(query));
       // searchBase.setPredicate(movie -> Double.toString(movie.getImdbRating()).contains(query));
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
}
