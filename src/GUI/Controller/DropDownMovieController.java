package GUI.Controller;

import BE.Category;
import GUI.Model.CategoryModel;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.List;

public class DropDownMovieController extends BaseController {
    @FXML
    public ComboBox cbxDropDown1, cbxDropDown2, cbxDropDown3;
    @FXML
    public Button btnCancel, btnSave, getBtnClear;
    private CategoryModel categoryModel;
    private MovieModel movieModel;

    private String categoryOne;
    private String categoryTwo;
    private String categoryThree;


    @Override
    public void setup() {
        categoryModel = getModel().getCategoryModel();
        movieModel = getModel().getMovieModel();
        List<Category> categoryList = categoryModel.getObservableCategories();
        //Adds categories to Combobox
        cbxDropDown1.getItems().addAll(categoryList);
        cbxDropDown2.getItems().addAll(categoryList);
        cbxDropDown3.getItems().addAll(categoryList);
        cbxDropDown1.getSelectionModel().select(null);
        cbxDropDown2.getSelectionModel().select(null);
        cbxDropDown3.getSelectionModel().select(null);


    }

    public void handelCancel(ActionEvent actionEvent) {
        closeWindow(btnCancel);
    }

    public void handelSave(ActionEvent actionEvent) throws Exception {
        compareCategories();
        Category category1 = (Category) cbxDropDown1.getSelectionModel().getSelectedItem();
        Category category2 = (Category) cbxDropDown2.getSelectionModel().getSelectedItem();
        Category category3 = (Category) cbxDropDown3.getSelectionModel().getSelectedItem();


        if(category1 != null) {
            categoryModel.addCategoryToMovie(category1, movieModel.createdMovie);
        }
        if(category2 != null) {
            categoryModel.addCategoryToMovie(category2, movieModel.createdMovie);
        }
        if(category3 != null) {
            categoryModel.addCategoryToMovie(category3, movieModel.createdMovie);
        }


        closeWindow(btnSave);
    }

    public void handleClearSelection(ActionEvent actionEvent) {

        cbxDropDown1.getSelectionModel().clearSelection();
        cbxDropDown2.getSelectionModel().clearSelection();
        cbxDropDown3.getSelectionModel().clearSelection();

    }

    private void compareCategories(){
        categoryOne = String.valueOf(cbxDropDown1.getSelectionModel().getSelectedItem());
        categoryTwo = String.valueOf(cbxDropDown2.getSelectionModel().getSelectedItem());
        categoryThree = String.valueOf(cbxDropDown3.getSelectionModel().getSelectedItem());

        if(categoryTwo.equals(categoryOne) ||categoryTwo.equals(categoryThree)){
            cbxDropDown2.getSelectionModel().clearSelection();
        }

        if(categoryThree.equals(categoryOne) || categoryThree.equals(categoryTwo))
        {
            cbxDropDown3.getSelectionModel().clearSelection();
        }
    }
}
