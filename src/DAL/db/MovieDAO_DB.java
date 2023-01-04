package DAL.db;

import BE.Movie;
import DAL.IMovieDataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

/*
public class MovieDAO_DB implements IMovieDataAccess
{
    private DataBaseConnecter dataBaseConnecter;

    public MovieDAO_DB()
    {
        dataBaseConnecter = new DataBaseConnecter();
    }

    @Override
    public List<Movie> getAllMovie() throws Exception {

        ArrayList<Movie> allMovie =new ArrayList<>();

        try (Connection conn = dataBaseConnecter.getConnection()) {
            String sql = "SELECT * FROM Movie;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("Id");
                String movieTitle = rs.getString("MovieTitle");
                String category = rs.getString("Category");
                int year = rs.getInt("Year");
                double imdbrating = rs.getDouble("Imdbrating");
                int personalrating = rs.getInt("Personalrating");
                double lastviewed = rs.getDouble("Lastviewed");
                String filepath = rs.getString("Filepath");

                Movie movie = new Movie (id, movieTitle, category, year, imdbrating, personalrating, lastviewed, filepath);
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
    public Movie createMovie(String movieTitle, String category, Integer year, Double imdbrating,
                             Integer personalrating, Double lastviewed, String filepath) throws Exception {
        return null;
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {

    }

    @Override
    public Movie deleteMovies(Movie movie) throws Exception {
        return null;
    }
}
*/
