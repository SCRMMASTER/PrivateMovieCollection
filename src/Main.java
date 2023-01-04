import GUI.Controller.PrivateMovieController;
import GUI.Model.MovieModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GUI/View/PrivateMovieView.fxml"));
        Parent root = loader.load();


        PrivateMovieController controller = loader.getController();
        //controller.setModel(new MovieModel());
        //controller.setup();

        primaryStage.setTitle("Movie Collection");
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(700);

        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
