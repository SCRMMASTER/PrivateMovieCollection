package BLL.unit;

import BE.Movie;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;

public class MovieSearcher {

    /**
     * Lambda expression for search function, for a movie with a title or year or imdbrating.
     * @param searchBase
     * @param query
     */
    public void search(FilteredList<Movie> searchBase, String query) {
        searchBase.predicateProperty().bind(Bindings.createObjectBinding(()->
                movie -> movie.getMovieTitle().toLowerCase().contains(query.toLowerCase())||
                        Integer.toString(movie.getYear()).contains(query)||
                        Double.toString(movie.getImdbRating()).contains(query)
        ));
    }
}
