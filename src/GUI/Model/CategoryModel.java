package GUI.Model;

import BE.Category;
import BE.Movie;
import BLL.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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
    /*
public void createNewCategory(String categoryname) throws Exception{
    Category mCategory = categoryManager.createNewCategory(categoryname);
    categoriesToBeViewed.add(mCategory);
}

     */

    public void createNewCategory(String Genre) throws Exception {
        Category c = categoryManager.createNewCategory(Genre);
        categoriesToBeViewed.add(c);
    }


    public void deleteCategory(Category selectedCategory) throws Exception{
        categoryManager.deleteCategory(selectedCategory);
        categoriesToBeViewed.remove(selectedCategory);
    }

    public void addCategoryToMovie(Category category, Movie movie) throws Exception{
        categoryManager.addCategoryToMovie(category, movie);
    }
    public void getAllMoviesFromCategory(Category category){
        categoryManager.getAllMoviesFromCategory(category);
    }
}
