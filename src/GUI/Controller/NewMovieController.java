package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewMovieController {

    public Button btnNext, btnCancel, btnChoose;
    public Label lblFile, lblTitle, lblIMDBRating, lblPersonalRating, lblDate;
    public TextField txtfFile, txtfTitle, txtfIMDBRating ,txtfPersonalRating, txtfDate;


    public void handelNext(ActionEvent event) {
    }

    public void handleButtonCancel(ActionEvent actionEvent) {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void handleChoose(ActionEvent event) {
    }
}
