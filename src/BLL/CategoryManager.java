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

    //Retrieve alle Categories from database table
    public List<Category> getAllCategories() throws Exception
    {
        return categoryDAO_DB.getAllCategories();

    //Create new category(Genre)

    }
    public Category createNewCategory(String Genre) throws Exception
    {
        return categoryDAO_DB.createCategory(Genre);


    }
    //Delete Category(genre)

    public void deleteCategory(Category selectedCategory) throws Exception
    {
        categoryDAO_DB.deleteCategory(selectedCategory);
    }


    public void addCategoryToMovie(Category category, Movie movie) throws Exception {
        categoryDAO_DB.addCategoryToMovie(category, movie);
    }
}
