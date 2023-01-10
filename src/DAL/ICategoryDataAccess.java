/*
 * Created by
 * Mathias, Kasper, Magnus and Jesper
 */
package DAL;

import BE.Category;

import java.util.List;

public interface ICategoryDataAccess {
    public List<Category> getAllCategories() throws Exception;
    public Category createCategory(String Genre) throws Exception;
    public void deleteCategory(Category category) throws Exception;
    
}
