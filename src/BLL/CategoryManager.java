/*
 * Created by
 * Mathias, Kasper, Magnus and Jesper
 */
package BLL;

import BE.Category;
import DAL.ICategoryDataAccess;
import DAL.db.CategoryDAO_DB;

import java.util.List;

public class CategoryManager {

    private ICategoryDataAccess CategoryDAO_DB;

    public CategoryManager(){

        CategoryDAO_DB = new CategoryDAO_DB();

    }

    //Retrieve alle Categories from database table
    public List<Category> getAllCategories() throws Exception
    {
        return CategoryDAO_DB.getAllCategories();

    //Create new category(Genre)

    }
    public Category createNewCategory(int id, String Genre) throws Exception
    {
        return CategoryDAO_DB.createCategory(id, Genre);

    }
    //Delete Category(genre)

    public void deletePlaylist(Category selectedCategory) throws Exception {

        CategoryDAO_DB.deleteCategory(selectedCategory);
    }


}
