package GUI.Controller;

import GUI.Model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

import java.util.Objects;

public class NewCategoryController extends BaseController {
    @FXML
    public Button btnCancel, btnDone;
    @FXML
    public TextField txtfCategory;

    private CategoryModel categoryModel;


    @Override
    public void setup() {
       categoryModel = getModel().getCategoryModel();
    }
    //Closes the window
    @FXML
    private void handelCancel(ActionEvent actionEvent) {
        closeWindow(btnCancel);
    }
    //Saves the category if the right conditions are met
    @FXML
    private void handelDone(ActionEvent actionEvent) {
        if (!txtfCategory.getText().isEmpty()) {
            try {
                String categoryName = txtfCategory.getText();
                categoryModel.createNewCategory(categoryName);

                closeWindow(btnDone);

            } catch(Exception e){
                e.printStackTrace();
                displayError(e);
            }
        } else {
            showAlert();
        }
    }
    //Handles if the conditions are not met
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Please enter the name of the category");
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("PopUp.css")).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }
}
