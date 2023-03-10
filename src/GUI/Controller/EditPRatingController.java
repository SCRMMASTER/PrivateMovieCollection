package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import java.time.LocalDate;
import java.util.Objects;

public class EditPRatingController extends BaseController {
    @FXML
    private Button btnCancel, btnDone;
    @FXML
    private TextField txtfEditPRating;
    private MovieModel movieModel;

    /**
     * The setup for the editPersonRating.
     */
    @Override
    public void setup() {
        movieModel = getModel().getMovieModel();
        txtfEditPRating.setText(String.valueOf(movieModel.getSelectedMovie().getPersonalRating()));
    }

    /**
     * Updating the personal rating on a movie, if the right conditions are met
     */
    @FXML
    private void handleDone(ActionEvent actionEvent) {
        try {
            int id = movieModel.selectedMovie.getId();
            String title = movieModel.selectedMovie.getMovieTitle();
            double imdbRaing = movieModel.selectedMovie.getImdbRating();
            int updatedPersonalRating = Integer.parseInt(txtfEditPRating.getText());
            String filepath = movieModel.selectedMovie.getFilePath();
            LocalDate lastview = movieModel.selectedMovie.getLastViewed();
            int year = movieModel.selectedMovie.getYear();

            if (!txtfEditPRating.getText().isEmpty() && updatedPersonalRating <= 10 && updatedPersonalRating >= 0) {
                Movie updatedMovie = new Movie(id, title, imdbRaing, updatedPersonalRating, filepath, lastview, year);
                movieModel.personalRating(updatedMovie);

                closeWindow(btnDone);
            }
            else {
                showAlert();
            }
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    @FXML
    /**
     * Close the window.
     */
    private void handleCancel(ActionEvent actionEvent) {
        closeWindow(btnCancel);
    }

    /**
     * Shows an alert window if the right conditions are not met
     */
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Please select a number between 0 and 10");
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("PopUp.css")).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }
}