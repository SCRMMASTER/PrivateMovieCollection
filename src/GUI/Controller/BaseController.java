package GUI.Controller;

import GUI.Model.BaseModel;
import GUI.Model.MovieModel;

public abstract class BaseController {

    private BaseModel baseModel;

    public void setModel(BaseModel baseModel)
    {
        this.baseModel = baseModel;
    }

    public BaseModel getModel()
    {
        return baseModel ;
    }

    public abstract void setup() throws Exception;
}
