/*
*
* Created by Kasper, Mathias, Magnus and Jesper
*
 */
/*
 * Created by
 * Mathias, Kasper, Magnus and Jesper
 */
package BE;

//Declare variables for the Movie object.
public class Movie {

    int id;

    String movieTitle;

    String category;

    int year;

    double imdbRating;

    int personalRating;

    double lastViewed;

    String filePath;


    public Movie(int id, String movieTitle,double imdbRating,int personalRating,String filePath,double lastViewed,int year){

        this.id = id;
        this.movieTitle = movieTitle;
        this.imdbRating = imdbRating;
        this.personalRating = personalRating;
        this.filePath = filePath;
        this.lastViewed = lastViewed;
        this.year = year;

        // the getter's and setter's

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(int personalRating) {
        this.personalRating = personalRating;
    }

    public double getLastViewed() {
        return lastViewed;
    }
    public void setLastViewed(double lastViewed){
        this.lastViewed = lastViewed;
    }
    public String getFilePath(){
        return filePath;
    }
    public void setFilePath(String filePath){
        this.filePath = filePath;

    }
}
