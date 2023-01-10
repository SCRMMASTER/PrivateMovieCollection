package GUI.Model;

import BE.Category;
import BE.Movie;
import BLL.CategoryManager;
import GUI.Controller.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public class CategoryModel {

    private ObservableList<Category> categoriesToBeViewed;
    private CategoryManager categoryManager;

    public CategoryModel() throws Exception {
        categoryManager = new CategoryManager();
        categoriesToBeViewed = FXCollections.observableArrayList();
        categoriesToBeViewed.addAll(categoryManager.getAllCategories());
    }



    public ObservableList<Category> getObservableCategories(){
        return categoriesToBeViewed;
    }


    public void createCategory(int Id, String Genre) throws Exception {
        Category c = categoryManager.createNewCategory(Id, Genre);

    }
    public void deleteCategory(Category selectedCategory) throws Exception{
        categoryManager.deleteCategory(selectedCategory);
        categoriesToBeViewed.remove(selectedCategory);
    }

    public void addCategoryToMovie(Category category, Movie movie) throws Exception{
        categoryManager.addCategoryToMovie(category, movie);
    }
}
