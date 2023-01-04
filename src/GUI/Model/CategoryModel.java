package GUI.Model;

import BE.Category;
import BLL.CategoryManager;


public class CategoryModel {
    private CategoryManager CategoryManager;

    public CategoryModel() throws Exception {
        CategoryManager = new CategoryManager();
    }

    public void createCategory(int Id, String Genre) throws Exception {
        Category c = CategoryManager.createNewCategory(Id, Genre);

    }
    public void deleteCategory(Category selectedCategory) throws Exception{
        CategoryManager.deleteCategory(selectedCategory);
    }
}
