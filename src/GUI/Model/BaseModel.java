package GUI.Model;

public class BaseModel {

    private CategoryModel categoryModel;
    private MovieModel movieModel;

    /**
     * Constructor for the BaseModel. Making the two Models for use in the controllers.
     */
    public BaseModel() throws Exception {
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }
}
