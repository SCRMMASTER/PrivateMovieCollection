package GUI.Controller;

import GUI.Model.BaseModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class BaseController {
    private BaseModel baseModel;

    public void setModel(BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public BaseModel getModel() {
        return baseModel ;
    }

    public abstract void setup() throws Exception;

    /**
     * Displaying an error if something is wrong, if there is no other alert window.
     */
    public void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    /**
     * Closes a window based on the FXML button.
     * @param btn
     */
    public void closeWindow(Button btn){
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
