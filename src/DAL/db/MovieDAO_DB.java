package DAL.db;

import BE.Movie;
import DAL.IMovieDataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.crypto.Data;
import java.sql.*;

import java.util.ArrayList;

import java.util.List;


public class MovieDAO_DB implements IMovieDataAccess
{
    private DataBaseConnecter dataBaseConnecter;

    public MovieDAO_DB()
    {
        dataBaseConnecter = new DataBaseConnecter();
    }

    @Override
    public List<Movie> getAllMovie() throws Exception {

        ArrayList<Movie> allMovie = new ArrayList<>();

        try (Connection conn = dataBaseConnecter.getConnection()) {
            String sql = "SELECT * FROM Movie;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("Id");
                String movieTitle = rs.getString("Title");
                double imdbrating = rs.getDouble("IMDB_Rating");
                int personalrating = rs.getInt("Personal_Rating");
                String filepath = rs.getString("FileLink");
                double lastviewed = rs.getDouble("LastView");
                int year = rs.getInt("Year");

                Movie movie = new Movie (id,movieTitle,imdbrating,personalrating,filepath,lastviewed,year);
                allMovie.add(movie);
            }
            return allMovie;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get movies from database", ex);
        }
    }

    @Override
    public Movie createMovie(String movieTitle, Double imdbrating, int personalrating, String filepath, Double lastviewed, int year) throws Exception {
        //Creating a movie in the database by using a SQL query.
        String sql = "INSERT INTO Movie (Title, IMDB_Rating, Personal_Rating, FileLink, LastView, Year) VALUES (?,?,?,?,?,?);";
        try(Connection conn = dataBaseConnecter.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Binding parameters.
            stmt.setString(1,movieTitle);
            stmt.setDouble(2, imdbrating);
            stmt.setInt(3, personalrating);
            stmt.setString(4, filepath);
            stmt.setDouble(5, lastviewed);
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
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not create movie", ex);
        }
    }

    @Override
    public Movie deleteMovies(Movie movie) throws Exception {
        //Delete the selected movie based on a specific id.
        String sql = "DELETE FROM Movie WHERE id = ?";
        try(Connection conn = dataBaseConnecter.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, movie.getId());


            int rowsDeleted = stmt.executeUpdate();
            // It checks if there was a row that have been deletet, that means if it is less than 0.
            if (rowsDeleted > 0)
            {
                System.out.println("Movie was successfully deleted");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not delete movie", ex);
        }
        return movie;
    }
}

