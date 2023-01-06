package GUI.Controller;

import BE.Movie;
import GUI.Model.CategoryModel;
import GUI.Model.MovieModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PrivateMovieController implements Initializable {
    @FXML
    public ListView lstCategory;
    @FXML
    public TableView<Movie> tblMovie;
    @FXML
    public TextField txtMovieSearch;
    @FXML
    private Button btnaddCategory, btndeleteCategory, btnaddMovie, btndeleteMovie, btnPLay;
    @FXML
    private TableColumn<Movie, String> ColYear, ColIMDB, ColPRating, ColTitle;

    public MovieModel movieModel;

    private CategoryModel categoryModel;



    public void initialize(URL location, ResourceBundle resources) {


      try {
            movieModel = new MovieModel();
          categoryModel = new CategoryModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        lstCategory.setItems(categoryModel.getObservableCategories());



        if (tblMovie.getSelectionModel().getSelectedItem() != null){
            btnPLay.setDisable(false);
        }
        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                try {
            movieModel.searchMovie(newValue);
        } catch (Exception e) {
            //displayError(e);
        }
    });

    tblMovie.setItems(movieModel.getObservableMovies());

    ColTitle.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getMovieTitle()));
    ColIMDB.setCellValueFactory(c -> new SimpleObjectProperty(String.valueOf(c.getValue().getImdbRating())));
    ColPRating.setCellValueFactory(c -> new SimpleObjectProperty(String.valueOf(c.getValue().getPersonalRating())));
    ColYear.setCellValueFactory(c -> new SimpleObjectProperty(String.valueOf(c.getValue().getYear())));


}



    public void handeladdMovie(ActionEvent actionEvent) throws IOException {
        // Finds where the fxml is located.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/View/NewMovieView.fxml"));
        // Loads the stage.
        Parent root = fxmlLoader.load();
        // Makes the new stage.
        Stage stage = new Stage();
        // Title of the stage.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new movie");
        stage.setScene(new Scene(root));
        // The stage is then displayed and the program waits for
        // the user to interact with the delete song dialog.
        stage.showAndWait();
    }

    public void handelDeleteMovie(ActionEvent actionEvent) {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you wanna delete this movie?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK) {
               movieModel.deleteMovie(tblMovie.getSelectionModel().getSelectedItem());
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

    public void handelPlay(ActionEvent actionEvent) throws IOException {

        //File file = new File("/Users/magnus/Documents/IMG_iii1652.MOV");
        //Desktop.getDesktop().open(file);


        File name = new File(tblMovie.getSelectionModel().getSelectedItem().getFilePath());
        System.out.println("kuisfhbd");

        Desktop.getDesktop().open(name);

    }
}
