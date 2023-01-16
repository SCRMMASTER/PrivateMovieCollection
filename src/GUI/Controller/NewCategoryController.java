package GUI.Controller;

import BE.Category;
import GUI.Model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

public class NewCategoryController extends BaseController {
    @FXML
    public Button btnCancel, btnDone;
    @FXML
    public TextField txtfCategory;

    private CategoryModel categoryModel;


    @Override
    public void setup() throws Exception {
       categoryModel = getModel().getCategoryModel();
    }
@FXML
    private void handelCancel(ActionEvent actionEvent) {
        closeWindow(btnCancel);
    }
@FXML
    private void handelDone(ActionEvent actionEvent) {
        if (!txtfCategory.getText().isEmpty()) {
            try {
                String categoryname = txtfCategory.getText();
                categoryModel.createNewCategory(categoryname);
                System.out.println("The Category was added " + categoryname);

                closeWindow(btnDone);

            } catch(Exception e){
                e.printStackTrace();
                displayError(e);
            }
        } else {
            showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Please enter the name of the category");
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }
}
