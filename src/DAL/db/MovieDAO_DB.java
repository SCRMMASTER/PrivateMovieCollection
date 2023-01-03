package DAL.db;

import BE.Movie;

import javax.xml.crypto.Data;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
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

        ArrayList<Movie> allMovie =new ArrayList<>();

        try (Connection conn = dataBaseConnecter.getConnection()) {
            String sql = "SELECT * FROM Movie;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("Id");
                String
            }
        }
    }

    @Override
    public Movie createMovie(String movieTitle, String category, Integer year, Double imdbrating, Integer personalrating, Double lastviewed, String filepath) throws Exception {
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
