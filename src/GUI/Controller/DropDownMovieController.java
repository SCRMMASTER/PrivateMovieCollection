package GUI.Controller;

import BE.Category;
import GUI.Model.CategoryModel;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class DropDownMovieController extends BaseController {
    @FXML
    private ComboBox cbxDropDown1, cbxDropDown2, cbxDropDown3;
    @FXML
    private Button btnCancel, btnSave;
    @FXML
    private Button btnClear;
    private CategoryModel categoryModel;
    private MovieModel movieModel;

    @Override
    public void setup() {
        categoryModel = getModel().getCategoryModel();
        movieModel = getModel().getMovieModel();
        //Adds categories to Combobox
        cbxDropDown1.getItems().addAll(categoryModel.getObservableCategories());
        cbxDropDown2.getItems().addAll(categoryModel.getObservableCategories());
        cbxDropDown3.getItems().addAll(categoryModel.getObservableCategories());
        cbxDropDown1.getSelectionModel().select(null);
        cbxDropDown2.getSelectionModel().select(null);
        cbxDropDown3.getSelectionModel().select(null);
    }
    @FXML
    private void handelCancel(ActionEvent actionEvent) {
        closeWindow(btnCancel);
    }
    @FXML
    private void handelSave(ActionEvent actionEvent) throws Exception {
        try {
            Category category1 = (Category) cbxDropDown1.getSelectionModel().getSelectedItem();
            Category category2 = (Category) cbxDropDown2.getSelectionModel().getSelectedItem();
            Category category3 = (Category) cbxDropDown3.getSelectionModel().getSelectedItem();

            if (category1 != null) {
                categoryModel.addCategoryToMovie(category1, movieModel.createdMovie);
            }
            if (category2 != null || category2 != category1) {
                categoryModel.addCategoryToMovie(category2, movieModel.createdMovie);
            }
            if (category3 != null || category3 != category2) {
                categoryModel.addCategoryToMovie(category3, movieModel.createdMovie);
            }
            closeWindow(btnSave);
        } catch (Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClearSelection(ActionEvent actionEvent) {
        cbxDropDown1.getSelectionModel().clearSelection();
        cbxDropDown2.getSelectionModel().clearSelection();
        cbxDropDown3.getSelectionModel().clearSelection();

    }
}
