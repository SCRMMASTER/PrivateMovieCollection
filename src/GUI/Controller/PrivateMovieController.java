package GUI.Controller;

import BE.Category;
import BE.Movie;
import GUI.Model.CategoryModel;
import GUI.Model.MovieModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
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
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class PrivateMovieController extends BaseController {
    @FXML
    private ListView<Category> lstCategory;
    @FXML
    private TableView<Movie> tblMovie;
    @FXML
    private TextField txtMovieSearch;
    @FXML
    private Button btnaddCategory, btndeleteCategory, btnaddMovie, btndeleteMovie, btnPLay, btnEditPRating, btnClose, btndeSelect;
    @FXML
    private TableColumn<Movie, String> ColYear, ColIMDB, ColPRating, ColTitle;
    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private Category selectedCategory;
    private Movie selectedMovie;
    //The first things when opening the window
    @Override
    public void setup() throws Exception {
        movieModel = getModel().getMovieModel();
        categoryModel = getModel().getCategoryModel();
        tblMovie.setItems(movieModel.getFilteredmovies());
        lstCategory.setItems(categoryModel.getObservableCategories());
        //Looks if there is a selected movie then you can hit play
        if (tblMovie.getSelectionModel().getSelectedItem() != null) {
            btnPLay.setDisable(false);
        }
        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                movieModel.searchMovie(newValue);
            } catch (Exception e) {
               displayError(e);
            }
        });


        ColTitle.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getMovieTitle()));
        ColIMDB.setCellValueFactory(c -> new SimpleObjectProperty<>(String.valueOf(c.getValue().getImdbRating())));
        ColPRating.setCellValueFactory(c -> new SimpleObjectProperty<>(String.valueOf(c.getValue().getPersonalRating())));
        ColYear.setCellValueFactory(c -> new SimpleObjectProperty<>(String.valueOf(c.getValue().getYear())));

       for(int i = 0; i <= movieModel.getObservableMovies().size()-1; i++){
           LocalDate lastviewed = movieModel.getObservableMovies().get(i).getLastViewed();

           int rating = movieModel.getObservableMovies().get(i).getPersonalRating();
           //Checking for lastviewed date
           LocalDate years = lastviewed.plusYears(2);
           //If both the lastviewed is over 2 year and the personal rating is under 6
           if(Objects.equals(lastviewed,years) && rating<=6){
               deleteMovieBasedOnTime(i);
               }
           }
    }
    //Opens the NewMovieView
    @FXML
    private void handeladdMovie(ActionEvent actionEvent) throws IOException {
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
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("PopUp.css")).toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);

        // The stage is then displayed and the program waits for
        // the user to interact with the delete song dialog.
        stage.showAndWait();
    }
    //Opens the alert window and ask the user if he really wants to delete the movie
    @FXML
    private void handelDeleteMovie(ActionEvent actionEvent) {
        if(tblMovie.getSelectionModel().getSelectedItem() != null) {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Are you sure you wanna delete this movie?");
                alert.initStyle(StageStyle.UNDECORATED);

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("PopUp.css")).toExternalForm());
                dialogPane.getStyleClass().add("myDialog");

                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    movieModel.deleteMovie(tblMovie.getSelectionModel().getSelectedItem());
                }
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }
        } else {
            showAlert();
        }
    }
    //Opens the NewCategoryView
    @FXML
    private void handeladdCategory(ActionEvent actionEvent) throws Exception {
        // Finds where the fxml is located.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/NewCategoryView.fxml"));

        // Loads the stage.
        Parent root = loader.load();

        NewCategoryController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        // Makes the new stage.
        Stage stage = new Stage();

        // Title of the stage<
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("PopUp.css")).toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);

        stage.showAndWait();
    }
    //Opens the alert window and ask the user if he really wants to delete the category
    @FXML
    private void handeldeleteCategory(ActionEvent actionEvent) {
        if(lstCategory.getSelectionModel().getSelectedItem() != null) {
            try {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Are you sure you wanna delete this category?");
                alert.initStyle(StageStyle.UNDECORATED);

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("PopUp.css")).toExternalForm());
                dialogPane.getStyleClass().add("myDialog");

                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    System.out.println("you have now deleted the category");
                    categoryModel.deleteCategory(lstCategory.getSelectionModel().getSelectedItem());
                }
            } catch (Exception e) {

                displayError(e);
                e.printStackTrace();
            }
        } else {
            showAlert();
        }
    }
    //Checks if you have selected a movie then you can play it if not n alert window opens
    @FXML
    private void handelPlay(ActionEvent actionEvent) throws Exception {
        if(tblMovie.getSelectionModel().getSelectedItem() != null) {
            File name = new File("Resources/Movies/" + tblMovie.getSelectionModel().getSelectedItem().getFilePath());
            editLastview(tblMovie.getSelectionModel().getSelectedItem());
            Desktop.getDesktop().open(name);
        }
        else {
            showAlert();
        }
    }
    //Closes the application
    @FXML
    private void handleCloseApp(ActionEvent actionEvent) {
        closeWindow(btnClose);
    }
    //Views the movies if there is a category selected, and if no category is selected then view all movies
    @FXML
    private void OnCategoryClicked(MouseEvent mouseEvent)throws Exception {
        if(selectedCategory == lstCategory.getSelectionModel().getSelectedItem()){
            movieModel.reloadAllMovies();
            selectedCategory = null;
            lstCategory.getSelectionModel().clearSelection();
            return;

        }
        selectedCategory = lstCategory.getSelectionModel().getSelectedItem();

        if (selectedCategory != null) {
            categoryModel.getAllMoviesFromCategory(selectedCategory);
            movieModel.setMoviesToBeViewed(selectedCategory.getMovie());
        }
    }
    //Opens the EditPRatingView if selected movie
    @FXML
    private void handleEditPRating (ActionEvent actionEvent) throws IOException {
            Movie selectedMovie = tblMovie.getSelectionModel().getSelectedItem();
            movieModel.setSelectedMovie(selectedMovie);

            //Wrapped in if statement to avoid run-time error.
        if (selectedMovie != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/EditPRatingView.fxml"));
            AnchorPane pane = loader.load();

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
        else {
            showAlert();
        }
    }
    //Opens the alert window if the movie is over 2 years since last view and has a personal rating under 6
    @FXML
    private void deleteMovieBasedOnTime(int i) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to delete " + movieModel.getObservableMovies().get(i).getMovieTitle() + " with a score of less than 6 and has not been seen in 2 years");
        alert.initStyle(StageStyle.UNDECORATED);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            movieModel.deleteMovie(movieModel.getObservableMovies().get(i));
        }
    }
    //Updating last view when playing a movie
    @FXML
    private void editLastview(Movie updatedDate) throws Exception {
        int id = updatedDate.getId();
        String title  = updatedDate.getMovieTitle();
        double imdbRating = updatedDate.getImdbRating();
        int personalRating = updatedDate.getPersonalRating();
        String filepath = updatedDate.getFilePath();
        LocalDate updatedLastview = LocalDate.now();
        int year = updatedDate.getYear();

        updatedDate = new Movie(id,title,imdbRating, personalRating, filepath, updatedLastview, year);
        movieModel.editLastView(updatedDate);
    }
    //The alert window if you have not selected a movie or a category
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Please select a movie/category");
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("PopUp.css")).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }
    //Checking if a movie is selected/clicked
    public void onMovieClicked(MouseEvent mouseEvent) {
        if(selectedMovie == tblMovie.getSelectionModel().getSelectedItem()){
            selectedMovie = null;
            tblMovie.getSelectionModel().clearSelection();

        }
        else {
            selectedMovie = tblMovie.getSelectionModel().getSelectedItem();
        }
    }
}


