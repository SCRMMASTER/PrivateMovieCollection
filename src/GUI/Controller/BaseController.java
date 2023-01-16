package GUI.Controller;

import GUI.Model.BaseModel;
import GUI.Model.MovieModel;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public abstract class BaseController {
    private BaseModel baseModel;

    public void setModel(BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public BaseModel getModel() {
        return baseModel ;
    }

    public abstract void setup() throws Exception;

    public void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }
    public void closeWindow(Button btn){
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
