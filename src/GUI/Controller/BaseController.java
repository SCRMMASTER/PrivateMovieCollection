package GUI.Controller;

import BE.Movie;
import GUI.Model.BaseModel;
import GUI.Model.MovieModel;

public abstract class BaseController {

    private BaseModel baseModel;
    private Movie selectedMovie;

    public void setModel(BaseModel baseModel)
    {
        this.baseModel = baseModel;
    }

    public BaseModel getModel()
    {
        return baseModel ;
    }

    public abstract void setup();

    public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }
    public SelectedMovie getSelectedMovie{return selectedMovie;}
}
