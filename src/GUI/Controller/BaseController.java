package GUI.Controller;

import GUI.Model.MovieModel;

public abstract class BaseController {

    private MovieModel model;

    public void setModel(MovieModel model){

    this.model = model;

    }

    public MovieModel model(){return model;}


    public abstract void setup();
}
