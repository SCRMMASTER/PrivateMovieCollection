/*
 * Created by
 * Mathias, Kasper, Magnus and Jesper
 */
package DAL;

import BE.Category;
import BE.Movie;

import java.util.List;

public interface ICategoryDataAccess {
    public List<Category> getAllCategories() throws Exception;
    public Category createCategory(int id, String Genre) throws Exception;
    public void deleteCategory(Category category) throws Exception;
    void addCategoryToMovie(Category category, Movie movie) throws Exception;

}
