package GUI.Controller;

import BE.Category;
import GUI.Model.CategoryModel;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class DropDownMovieController extends BaseController {
    public ComboBox cbxDropDown1, cbxDropDown2, cbxDropDown3;
    public Button btnCancel, btnSave;

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

    public void handelCancel(ActionEvent actionEvent) {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void handelSave(ActionEvent actionEvent) throws Exception {
        Category category1 = (Category) cbxDropDown1.getSelectionModel().getSelectedItem();
        Category category2 = (Category) cbxDropDown2.getSelectionModel().getSelectedItem();
        Category category3 = (Category) cbxDropDown3.getSelectionModel().getSelectedItem();


        if(category1 != null) {
            categoryModel.addCategoryToMovie(category1, movieModel.createdMovie);
        }
        if(category2 != null){
            categoryModel.addCategoryToMovie(category2, movieModel.createdMovie);
        }
        if(category3 != null){
            categoryModel.addCategoryToMovie(category3, movieModel.createdMovie);
        }

        Node source = (Node) actionEvent.getSource();
        Stage mStage = (Stage) source.getScene().getWindow();
        mStage.close();
    }
}
