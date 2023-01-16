package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class EditPRatingController extends BaseController {
    @FXML
    public Button btnCancel, btnDone;
    @FXML
    private TextField txtfEditPRating;
    private MovieModel movieModel;

    public void handleDone(ActionEvent actionEvent) throws Exception {
        int id = movieModel.selectedMovie.getId();
        String title  = movieModel.selectedMovie.getMovieTitle();
        double imdbRaing = movieModel.selectedMovie.getImdbRating();
        int updatedPersonalRating = Integer.parseInt(txtfEditPRating.getText());
        String filepath = movieModel.selectedMovie.getFilePath();
        LocalDate lastview = movieModel.selectedMovie.getLastViewed();
        int year = movieModel.selectedMovie.getYear();
        if(!txtfEditPRating.getText().isEmpty() && updatedPersonalRating <= 10 && updatedPersonalRating >= 0) {
            Movie updatedMovie = new Movie(id, title, imdbRaing, updatedPersonalRating, filepath, lastview, year);
            movieModel.personalRating(updatedMovie);

            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
        else {
            showAlert();
        }
    }
        // Cancel button
    public void handleCancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @Override
    public void setup() {
        movieModel = getModel().getMovieModel();
        txtfEditPRating.setText(String.valueOf(movieModel.getSelectedMovie().getPersonalRating()));
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Please select a number between 0 and 10");
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }
}