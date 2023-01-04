package GUI.Controller;

import javafx.event.ActionEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PrivateMovieController extends BaseController {

    @Override
    public void setup() {

    }

    public void addMovie(ActionEvent actionEvent) {
        System.out.println("Add movie");
    }

    public void deleteMovie(ActionEvent actionEvent) {
        System.out.println("Delete Movie");
    }

    public void addCategory(ActionEvent actionEvent) {
        System.out.println("AddCategory");
    }

    public void deleteCategory(ActionEvent actionEvent) {
        System.out.println("dekete categary");
    }

    public void playMovie(ActionEvent actionEvent) throws IOException {

        File file = new File("/Users/magnus/Documents/IMG_iii1652.MOV");
        Desktop.getDesktop().open(file);

    }
}
