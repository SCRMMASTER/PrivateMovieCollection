/*
 * Created by
 * Mathias, Kasper, Magnus and Jesper
 */
package DAL;

import BE.Category;
import BE.Movie;

import java.util.List;

public interface ICategoryDataAccess {
    List<Category> getAllCategories() throws Exception;
    Category createCategory(String Genre) throws Exception;
    void deleteCategory(Category category) throws Exception;
    void addCategoryToMovie(Category category, Movie movie) throws Exception;
    List<Movie> getAllMoviesFromCategory(Category category);
}
