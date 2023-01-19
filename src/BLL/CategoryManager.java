/*
 * Created by
 * Mathias, Kasper, Magnus and Jesper
 */
package BLL;

import BE.Category;
import BE.Movie;
import DAL.ICategoryDataAccess;
import DAL.db.CategoryDAO_DB;

import java.util.List;

public class CategoryManager {

    private ICategoryDataAccess categoryDAO_DB;

    public CategoryManager(){
        categoryDAO_DB = new CategoryDAO_DB();
    }

    /**
     * Retrieve alle Categories from category table in the database.
     */
    public List<Category> getAllCategories() throws Exception {
        return categoryDAO_DB.getAllCategories();
    }

    /**
     * Create new category.
     * @param Genre
     */
    public Category createNewCategory(String Genre) throws Exception {
        return categoryDAO_DB.createCategory(Genre);
    }

    /**
     * Delete Category
     * @param selectedCategory
     */
    public void deleteCategory(Category selectedCategory) throws Exception {
        categoryDAO_DB.deleteCategory(selectedCategory);
    }

    /**
     * Add Categories to a movie
     * @param category
     * @param movie
     */
    public void addCategoryToMovie(Category category, Movie movie) throws Exception {
        categoryDAO_DB.addCategoryToMovie(category, movie);
    }

    /**
     * Get all movies form a selected category.
     * @param category
     */
    public void getAllMoviesFromCategory(Category category){
        List<Movie> allMovieFromCategory = categoryDAO_DB.getAllMoviesFromCategory(category);
        category.setMovie(allMovieFromCategory);
    }
}
