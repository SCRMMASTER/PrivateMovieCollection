package GUI.Controller;

import BE.Category;
import BE.Movie;
import GUI.Model.BaseModel;
import GUI.Model.CategoryModel;
import GUI.Model.MovieModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class PrivateMovieController extends BaseController {
    @FXML
    public ListView<Category> lstCategory;
    @FXML
    public TableView<Movie> tblMovie;
    @FXML
    public TextField txtMovieSearch;
    public Button btndeSelect;
    @FXML
    private Button btnaddCategory, btndeleteCategory, btnaddMovie, btndeleteMovie, btnPLay, btnEditPRating, btnClose;
    @FXML
    private TableColumn<Movie, String> ColYear, ColIMDB, ColPRating, ColTitle;
    public MovieModel movieModel;
    public CategoryModel categoryModel;
    private Category selectedCategory;
    private int clickCounter;

    public PrivateMovieController() {
        try {
            movieModel = new MovieModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setup() throws Exception {


        movieModel = getModel().getMovieModel();
        categoryModel = getModel().getCategoryModel();
        tblMovie.setItems(movieModel.getObservableMovies());
        lstCategory.setItems(categoryModel.getObservableCategories());


        if (tblMovie.getSelectionModel().getSelectedItem() != null) {
            btnPLay.setDisable(false);
        }
        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                movieModel.searchMovie(newValue);
            } catch (Exception e) {
                //displayError(e);
            }
        });



        ColTitle.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getMovieTitle()));
        ColIMDB.setCellValueFactory(c -> new SimpleObjectProperty(String.valueOf(c.getValue().getImdbRating())));
        ColPRating.setCellValueFactory(c -> new SimpleObjectProperty(String.valueOf(c.getValue().getPersonalRating())));
        ColYear.setCellValueFactory(c -> new SimpleObjectProperty(String.valueOf(c.getValue().getYear())));

       for(int i = 0; i <= movieModel.getObservableMovies().size()-1; i++){
           LocalDate lastviewed = movieModel.getObservableMovies().get(i).getLastViewed();

           int rating = movieModel.getObservableMovies().get(i).getPersonalRating();

           LocalDate years = lastviewed.plusYears(2);

           if(Objects.equals(lastviewed,years) && rating<=6){
               deleteMovieBasedOnTime(i);
               }
           }
        }
    public void handeladdMovie(ActionEvent actionEvent) throws IOException {

        // Finds where the fxml is located.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/NewMovieView.fxml"));

        // Loads the stage.
        Parent root = loader.load();
        NewMovieController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        // Makes the new stage.
        Stage stage = new Stage();

        // Title of the stage.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        root.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);

        // The stage is then displayed and the program waits for
        // the user to interact with the delete song dialog.
        stage.showAndWait();
    }

    public void handelDeleteMovie(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you wanna delete this movie?");
            alert.initStyle(StageStyle.UNDECORATED);

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");

            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                movieModel.deleteMovie(tblMovie.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
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
        stage.setScene(new Scene(root));
        root.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);

        // The stage is then displayed and the program waits for
        // the user to interact with the delete song dialog.
        NewCategoryController controller = fxmlLoader.getController();
        controller.setModelCategory(categoryModel, lstCategory.getSelectionModel().getSelectedItem());
        stage.showAndWait();
    }

    public void handeldeleteCategory(ActionEvent actionEvent) {
        System.out.println("delete categary");
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you wanna delete this category?");
            alert.initStyle(StageStyle.UNDECORATED);

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");

            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                System.out.println("you have now deleted the category");
                categoryModel.deleteCategory(lstCategory.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {

            //displayError(e);
            e.printStackTrace();
        }
    }


    public void handelPlay(ActionEvent actionEvent) throws IOException {
        //File file = new File("/Users/magnus/Documents/IMG_iii1652.MOV");
        //Desktop.getDesktop().open(file);

        tblMovie.getSelectionModel().getSelectedItem().setLastViewed(LocalDate.now());
        File name = new File("Resources/Movies/" + tblMovie.getSelectionModel().getSelectedItem().getFilePath());
        System.out.println("kuisfhbd");

        Desktop.getDesktop().open(name);
    }

    public void handleCloseApp(ActionEvent actionEvent) {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void OnCategoryClicked(MouseEvent mouseEvent) {
        selectedCategory = lstCategory.getSelectionModel().getSelectedItem();

        if (selectedCategory != null) {
           categoryModel.getAllMoviesFromCategory(selectedCategory);
            tblMovie.setItems(FXCollections.observableArrayList(selectedCategory.getMovie()));


        }
        else
            tblMovie.setItems(movieModel.getObservableMovies());
            //lstCategory.getSelectionModel().clearSelection();


    }



    public void handleEditPRating (ActionEvent actionEvent) throws IOException {
            Movie selectedMovie = tblMovie.getSelectionModel().getSelectedItem();
            movieModel.setSelectedMovie(selectedMovie);

            //Wrapped in if statement to avoid run-time error.
            if (selectedMovie != null) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/GUI/View/EditPRatingView.fxml"));
                AnchorPane pane = (AnchorPane) loader.load();

                EditPRatingController controller = loader.getController();
                controller.setModel(super.getModel());
                controller.setup();

                //Creates the dialog Stage.
                Stage dialogWindow = new Stage();
                dialogWindow.setTitle("Edit Personal Rating");
                dialogWindow.initModality(Modality.WINDOW_MODAL);
                dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                Scene scene = new Scene(pane);
                scene.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
                dialogWindow.initStyle(StageStyle.UNDECORATED);
                dialogWindow.setScene(scene);

                //Opens window and waits for user input.
                dialogWindow.showAndWait();
            }
        }
    public void deleteMovieBasedOnTime(int i) throws Exception {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Do you wanna delete " + movieModel.getObservableMovies().get(i).getMovieTitle() + " with a score of less than 6 and has not been seen in 2 years");
            alert.initStyle(StageStyle.UNDECORATED);

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");

            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                movieModel.deleteMovie(movieModel.getObservableMovies().get(i));
            }
        }

}


