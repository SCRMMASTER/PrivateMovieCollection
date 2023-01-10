package GUI.Controller;

import GUI.Model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCategoryController extends BaseController {


    @FXML
    public TextField categoryName;
    @FXML
    public Button btnAdd,btnCancel;

    CategoryModel categoryModel;

    @Override
    public void setup() {
        categoryModel = getModel().getCategoryModel();
    }


    public void handleAddCategory(ActionEvent actionEvent)
    {

    }

    public void cancelNewCategory(ActionEvent actionEvent)
    {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
}
