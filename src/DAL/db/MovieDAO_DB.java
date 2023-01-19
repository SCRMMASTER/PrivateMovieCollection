package DAL.db;

import BE.Movie;
import DAL.IMovieDataAccess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDataAccess
{
    private DataBaseConnecter dataBaseConnecter;

    public MovieDAO_DB() {
        dataBaseConnecter = new DataBaseConnecter();
    }

    /**
     * Getting all the movies form the database.
     */
    @Override
    public List<Movie> getAllMovie() throws Exception {
        ArrayList<Movie> allMovie = new ArrayList<>();
        try (Connection conn = dataBaseConnecter.getConnection()) {
            //SQL statement.
            String sql = "SELECT * FROM Movie;";
            Statement stmt = conn.createStatement();
            //Run the statement.
            ResultSet rs = stmt.executeQuery(sql);
            //Keep adding as long as there is a next movie.
            while (rs.next()) {
                int id = rs.getInt("Id");
                String movieTitle = rs.getString("Title");
                double imdbrating = rs.getDouble("IMDB_Rating");
                int personalrating = rs.getInt("Personal_Rating");
                String filepath = rs.getString("FileLink");
                LocalDate lastviewed = rs.getDate("LastView").toLocalDate();
                int year = rs.getInt("Year");

                Movie movie = new Movie(id, movieTitle, imdbrating, personalrating, filepath, lastviewed, year);
                allMovie.add(movie);
            }
            return allMovie;

            } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not get movies from database", ex);
        }
    }


    /**
     * Creating a movie with the parameters
     * @param movieTitle
     * @param imdbrating
     * @param personalrating
     * @param filepath
     * @param lastviewed
     * @param year
     */
    @Override
    public Movie createMovie(String movieTitle, Double imdbrating, int personalrating, String filepath, LocalDate lastviewed, int year) throws Exception {
        //Creating a movie in the database by using a SQL query.
        String sql = "INSERT INTO Movie (Title, IMDB_Rating, Personal_Rating, FileLink, LastView, Year) VALUES (?,?,?,?,?,?);";
        try(Connection conn = dataBaseConnecter.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Binding parameters.
            stmt.setString(1,movieTitle);
            stmt.setDouble(2, imdbrating);
            stmt.setInt(3, personalrating);
            stmt.setString(4, filepath);
            stmt.setDate(5, Date.valueOf(lastviewed));
            stmt.setInt(6, year);

            //Run the SQL statement.
            stmt.executeUpdate();

            //Get the ID from the database
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if(rs.next()){
                id = rs.getInt(1);
        }
            //Create the movie.
            Movie movie = new Movie(id,movieTitle,imdbrating,personalrating,filepath,lastviewed,year);
            return movie;

        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not create movie", ex);
        }
    }

    /**
     * Deleting a movie from the database based on a selected id.
     * @param movie
     */
    @Override
    public Movie deleteMovies(Movie movie) throws Exception {
        //SQL statement.
        String sql = "DELETE FROM Movie WHERE id = ?";
        deleteCategoryFromMovie(movie);
        try(Connection conn = dataBaseConnecter.getConnection()) {
            //Prepared statement and binding the parameters.
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, movie.getId());

            int rowsDeleted = stmt.executeUpdate();
            // It checks if there was a row that have been deleted, that means if it is less than 0.
            if (rowsDeleted > 0) {
                System.out.println("Movie was successfully deleted");
            }

        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not delete movie", ex);
        }
        return movie;
    }

    /**
     * Getting the filepath for a movie through the database
     * @param selectedMovie
     */
  /**  public Movie getFilePath (Movie selectedMovie) throws Exception {
        String sql = " SELECT FROM Movie WHERE id = ?";

        try(Connection conn = dataBaseConnecter.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            stmt.setString(4, selectedMovie.getFilePath());

            if (rs.next()) {
                rs.getString(4);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not retrive filePath", ex);
        }
        return selectedMovie;
    } */
    /**
     * Deleting all movies from the CatMovie table based on a selected id.
     * @param movie
     */
    private void deleteCategoryFromMovie(Movie movie){
        try(Connection conn = dataBaseConnecter.getConnection()){
            //SQL statement.
            String sql = "DELETE FROM CatMovie WHERE MovieId = ?";
            //Prepared statement and binding the parameters.
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, movie.getId());
            //Run the statement.
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not remove songs from playlist");
        }
}

    /**
     * Editing the personalRating for a selected movie.
     * @param updatedMovie
     */
    public void personalRating(Movie updatedMovie) throws Exception {
        try (Connection conn = dataBaseConnecter.getConnection()) {
            //SQL statement.
            String sql = "UPDATE Movie SET Personal_Rating = ? WHERE Id = ?";
            //Prepared statement.
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Bind parameter
            stmt.setInt(1 , updatedMovie.getPersonalRating());
            stmt.setInt(2, updatedMovie.getId());
            //Run the statement.
            stmt.executeUpdate();

        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not edit Personal Rating...", ex);
        }
    }

    /**
     * Updating lastview for a movie in the database when the movie is opened.
     * @param updatedDate
     */
    public void editLastview(Movie updatedDate) throws Exception {
        try (Connection conn = dataBaseConnecter.getConnection()) {
            //SQL statement.
            String sql = "UPDATE Movie SET lastView = ? WHERE Id = ?";
            //Prepared statement.
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Bind parameter
            stmt.setDate(1 , Date.valueOf(updatedDate.getLastViewed()));
            stmt.setInt(2, updatedDate.getId());
            //Run the statement.
            stmt.executeUpdate();

        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not edit the date...", ex);
        }
    }
}

