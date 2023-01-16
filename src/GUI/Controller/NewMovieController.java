package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class NewMovieController extends BaseController{
    @FXML
    public Button btnNext, btnCancel, btnChoose;
    @FXML
    private TextField txtfFile, txtfTitle, txtfIMDBRating, txtfPersonalRating, txtfYear;
    private File mFile;
    public String fileMoviePath = "Resources/Movies";
    private Path target = Paths.get(fileMoviePath);
    private MovieModel movieModel;


    @Override
    public void setup() {
        movieModel = getModel().getMovieModel();
    }


    public void handelNext(ActionEvent actionEvent) throws Exception {
        String title = txtfTitle.getText();
        double imdbrating = Double.parseDouble(txtfIMDBRating.getText());
        int personalrating = Integer.parseInt(txtfPersonalRating.getText());
        String filepath = txtfFile.getText();
        LocalDate lastviewed = LocalDate.now();
        int year = Integer.parseInt(txtfYear.getText());


        Files.copy(mFile.toPath(), target.resolve(mFile.toPath().getFileName()));


                mFile = new File(fileMoviePath + "/" + mFile.getName());
                movieModel.createMovie(title, imdbrating, personalrating, filepath, lastviewed, year);

                System.out.println("Movie added: " + filepath + ", " + title + ", " + imdbrating +
                        ", " + personalrating + ", " + year);


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/DropDownMovieView.fxml"));
                // Loads the stage.
                Parent root = loader.load();
                // Makes the new stage.
                Stage stage = new Stage();
                // Title of the stage.
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                root.getStylesheets().add(getClass().getResource("PopUp.css").toExternalForm());
                stage.initStyle(StageStyle.UNDECORATED);

                DropDownMovieController controller = loader.getController();
                controller.setModel(super.getModel());
                controller.setup();

                // The stage is then displayed and the program waits for
                // the user to interact with the delete song dialog.
                stage.showAndWait();

                Node source = (Node) actionEvent.getSource();
                Stage mStage = (Stage) source.getScene().getWindow();
                mStage.close();
            }






    public void handleButtonCancel(ActionEvent actionEvent) {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();


        System.out.println(movieModel.getAllFilePaths());

    }
    public void handleChoose(ActionEvent actionEvent) {
        //Opens file browser to select a file
        Stage stage = new Stage();
        FileChooser mFileChooser = new FileChooser();
        mFileChooser.setTitle("Pick a Movie");
        mFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Movie Files (*.mp4, *.mpeg4)","*.mp4","*.mpeg4"),
                new FileChooser.ExtensionFilter("MP4 Files (*.mp4)", "*.mp4"),
                new FileChooser.ExtensionFilter("MPEG4 Files (*.mpeg4)","*.mpeg4")
        );
        mFile = mFileChooser.showOpenDialog(stage);
        if(mFile != null)
       txtfFile.setText(mFile.getName());


        System.out.println("Selected file " + mFile);
        //System.out.println(getMovieLength(mFile).toString());

    }

    public Duration getMovieLength(File file){
        Media mMedia = new Media("file:///" + file.getPath().replace("\\","/").replaceAll(" ","%20"));
        return mMedia.getDuration();
    }
}
