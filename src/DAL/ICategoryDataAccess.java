package DAL;

import BE.Category;

import java.util.List;

public interface ICategoryDataAccess {
    public List<Category> getAllCategories() throws Exception;
    public Category createCategory(int id, String Genre) throws Exception;
    public void deleteCategory(Category category) throws Exception;

}
