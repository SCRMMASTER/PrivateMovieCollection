package BLL.unit;

import BE.Movie;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;

public class MovieSearcher {


    public void search(FilteredList<Movie> searchBase, String query) {
        searchBase.predicateProperty().bind(Bindings.createObjectBinding(()->
                movie -> movie.getMovieTitle().toLowerCase().contains(query.toLowerCase())||
                        Integer.toString(movie.getYear()).contains(query)||
                        Double.toString(movie.getImdbRating()).contains(query)
        ));
    }
}
