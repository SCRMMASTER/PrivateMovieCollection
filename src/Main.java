import GUI.Controller.PrivateMovieController;
import GUI.Model.BaseModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/PrivateMovieView.fxml"));
        Parent root = loader.load();

        PrivateMovieController controller = loader.getController();
        controller.setModel(new BaseModel());
        controller.setup();


        primaryStage.setTitle("Movie Collection");
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
        primaryStage.setMaxHeight(620);
        primaryStage.setMaxWidth(700);
        primaryStage.setMinHeight(620);
        primaryStage.setMinWidth(700);
        primaryStage.setResizable(false);

        primaryStage.show();
        jinglePlay().play();



    }

    public static void main(String[] args) {
        launch(args);
    }

    private AudioClip jingle = null;

    public AudioClip jinglePlay() {

        if (jingle == null) {

            String src = getClass().getResource("jingle.mp3").toString();
            jingle = new AudioClip(src);


        }
        return jingle;

    }
}


