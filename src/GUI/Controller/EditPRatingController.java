package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPRatingController extends BaseController {
    @FXML
    public Button btnCancel, btnDone;
    @FXML
    private TextField txtfEditPRating;

    private MovieModel movieModel;

    public void handleDone(ActionEvent actionEvent) throws Exception
    {

        int updatedPersonalRating = Integer.parseInt(txtfEditPRating.getText());


    }
// Cancel button
    public void handleCancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
@Override
public void setup()
    {
        movieModel = getModel().getMovieModel();
    txtfEditPRating.setText(String.valueOf(movieModel.getSelectedMovie().getPersonalRating()));
    }
}