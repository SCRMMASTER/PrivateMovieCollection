package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PrivateMovieController extends BaseController {
    @FXML
    public ListView lstCategory;
    public TableView tblMovie;
    public TextField txtMovieSearch;
    @FXML
    private Button btnaddCategory, btndeleteCategory, btnaddMovie, btndeleteMovie;
    @FXML
    private TableColumn<Movie, String> ColTitle, ColYear, ColIMDB, ColPRating;

    @Override
    public void setup() {

    }
    /*
public void initialize(){
    txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) ->
    {
        try {
            MovieModel.searchSong(newValue);
        } catch (Exception e) {
            displayError(e);
        }
    });
}
*/


    public void handeladdMovie(ActionEvent actionEvent) throws IOException {
        System.out.println("Add movie");
        // Finds where the fxml is located.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/View/NewMovieView.fxml"));
        // Loads the stage.
        Parent root = fxmlLoader.load();
        // Makes the new stage.
        Stage stage = new Stage();
        // Title of the stage<
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new movie");
        stage.setScene(new Scene(root));
        // The stage is then displayed and the program waits for
        // the user to interact with the delete song dialog.
        stage.showAndWait();
    }

    public void handeldeleteMovie(ActionEvent actionEvent) {
        System.out.println("Delete Movie");
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you wanna delete this movie?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK) {
                System.out.println("you have now deleted the movie");
               // movieModel.deleteSong(movieModel.getSelectedSong());
            }
        } catch (Exception e){
            //displayError(e);

            e.printStackTrace();
        }
    }

    public void handeladdCategory(ActionEvent actionEvent) throws IOException {
        System.out.println("AddCategory");
        // Finds where the fxml is located.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/View/NewCategoryView.fxml"));
        // Loads the stage.
        Parent root = fxmlLoader.load();
        // Makes the new stage.
        Stage stage = new Stage();
        // Title of the stage<
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new category");
        stage.setScene(new Scene(root));
        // The stage is then displayed and the program waits for
        // the user to interact with the delete song dialog.
        stage.showAndWait();
    }

    public void handeldeleteCategory(ActionEvent actionEvent) {
        System.out.println("delete categary");
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you wanna delete this category?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK) {
                System.out.println("you have now deleted the category");
                // movieModel.deleteSong(movieModel.getSelectedSong());
            }
        } catch (Exception e){
            //displayError(e);

            e.printStackTrace();
        }
    }

    public void playMovie(ActionEvent actionEvent) throws IOException {

        //File file = new File("/Users/magnus/Documents/IMG_iii1652.MOV");
        //Desktop.getDesktop().open(file);

        File name = new File("/Users/magnus/Documents/GitHub/PrivateMovieCollection/src/MoviesDirectory/LOTR.mp4");
        Desktop.getDesktop().open(name);

    }

}
