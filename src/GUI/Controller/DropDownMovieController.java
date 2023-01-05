package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class DropDownMovieController {
    public ComboBox cbxDropDown1, cbxDropDown2, cbxDropDown3;
    public Button btnCancel, btnSave;

    public void initialize (){
        //Adds categories to Combobox
        cbxDropDown1.getItems().addAll("Action","Animation","Crime","Comedy","Fantasy","Horror","Romance","Thriller","War");
        cbxDropDown2.getItems().addAll("Action","Animation","Crime","Comedy","Fantasy","Horror","Romance","Thriller","War");
        cbxDropDown3.getItems().addAll("Action","Animation","Crime","Comedy","Fantasy","Horror","Romance","Thriller","War");
        cbxDropDown1.getSelectionModel().select("---");
        cbxDropDown2.getSelectionModel().select("---");
        cbxDropDown3.getSelectionModel().select("---");
    }

    public void handelCancel(ActionEvent actionEvent) {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void handelSave(ActionEvent actionEvent) {
        String category1 = (String) cbxDropDown1.getValue();
        String category2 = (String) cbxDropDown2.getValue();
        String category3 = (String) cbxDropDown3.getValue();

        Node source = (Node) actionEvent.getSource();
        Stage mStage = (Stage) source.getScene().getWindow();
        mStage.close();
    }
}
