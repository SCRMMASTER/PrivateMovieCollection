package GUI.Controller;

import BE.Category;
import GUI.Model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCategoryController {
    public Button btnCancel, btnDone;
    public TextField txtfCategory;
    private CategoryModel model;
    private Category selectedCategory;
    private BE.Category Category;

    public void  setModelCategory(CategoryModel model, Category category){
        this.model = model;
        this.selectedCategory = Category;

    }

    public void handelCancel(ActionEvent actionEvent) {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void hancelDone(ActionEvent actionEvent) {

        try{
            String categoryname = txtfCategory.getText();

            this.model.createNewCategory(categoryname);
            System.out.println("The Playlist was added " + categoryname);
            // This code closes the current window by getting a reference to the stage
            // and calling the close() method.
            Node source = (Node) actionEvent.getSource();
            Stage mStage = (Stage) source.getScene().getWindow();
            mStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not add song");
        }
    }
}
