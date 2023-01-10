package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;

public class EditPRatingController extends BaseController{

    private Movie selectedMovie;
    private MovieModel model;

    public void fillMovieIn(Movie movie) throws Exception {
        model = getModel().getMovieModel();

    }

    @Override
    public void setup() {

    }
}
