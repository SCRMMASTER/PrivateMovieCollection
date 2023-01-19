/*
<<<<<<< HEAD
* Created by
* Mathias, Kasper, Magnus and Jesper
*/

package DAL.db;

import BE.Category;
import BE.Movie;
import DAL.ICategoryDataAccess;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO_DB implements ICategoryDataAccess {
    private DataBaseConnecter dataBaseConnecter;

    public CategoryDAO_DB() {
        dataBaseConnecter = new DataBaseConnecter();
    }

    /**
     *Get all the Categories from the database table
     */
    public List<Category> getAllCategories() throws Exception {
        ArrayList<Category> allCategory = new ArrayList<>();

        try (Connection conn = dataBaseConnecter.getConnection()) {
            String sql = "SELECT * FROM Category;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //Keep adding as long as there is a next category.
            while (rs.next()) {
                int id = rs.getInt("Id");
                String Genre = rs.getString("Genre");

                Category category = new Category(id, Genre);
                allCategory.add(category);
            }
            return allCategory;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get Category form database", ex);
        }
    }

    /**
     * Create a new Category in the database table.
     * @param Genre
     */
    public Category createCategory(String Genre) throws Exception {
        //SQL Statement.
        String sql = "INSERT INTO Category (Genre) VALUES (?);";

        try (Connection conn = dataBaseConnecter.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Bind the parameters.
            stmt.setString(1, Genre);
            //Run the SQL statement.
            stmt.executeUpdate();
            //Get the generated ID from the DB.
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            //Create category object and send up the layers.

            Category category = new Category(id, Genre);
            return category;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create Genre", ex);
        }
    }

    /**
     * Delete a Category from the database table.
     * @param selectedCategory
     */
    public void deleteCategory(Category selectedCategory) throws Exception {
        deleteCategoryFromMovie(selectedCategory);
        try (Connection conn = dataBaseConnecter.getConnection()) {
            //SQL Statement.
            String sql = "DELETE FROM Category WHERE Id = ?;";
            //Prepared Statement.
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, (selectedCategory.getId()));
            //Run the statement.
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete Category...", ex);
        }
    }

    /**
     *Adding Category to a movie.
     * @param category
     * @param movie
     */
    public void addCategoryToMovie(Category category, Movie movie) throws Exception {
        try (Connection conn = dataBaseConnecter.getConnection()) {
            //SQL Statement.
            String sql = "INSERT INTO CatMovie VALUES(?,?)";
            //Prepared statement.
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, category.getId());
            stmt.setInt(2, movie.getId());
            //Run the statement.
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not add category to movie", ex);
        }
    }

    /**
     * Deleting a selected category from the catmovie table.
     * @param category
     */
    private void deleteCategoryFromMovie(Category category) {
        try (Connection conn = dataBaseConnecter.getConnection()) {
            //SQL statement.
            String sql = "DELETE FROM CatMovie WHERE CategoryId = ?";
            //Prepared statement.
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, category.getId());
            //Run the statement.
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not remove songs from playlist");
        }
    }

    /**
     * Getting all the movies form a selected category
     * @param category
     */
    public List<Movie> getAllMoviesFromCategory(Category category) {
        ArrayList<Movie> allMoviesInCategory = new ArrayList<>();
        try (Connection conn = dataBaseConnecter.getConnection()) {
            //SQL Statement.
            String sql = "SELECT m.* FROM CatMovie cm INNER JOIN Movie m ON m.Id = cm.MovieId WHERE cm.CategoryId = ?";
            //Prepared Statement.
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, category.getId());
            //Run the statement.
            ResultSet rs = stmt.executeQuery();
            //Adding movie to the list as long as there is a next.
            while (rs.next()) {
                int id = rs.getInt("Id");
                String movieTitle = rs.getString("Title");
                Double imdbRating = rs.getDouble("IMDB_Rating");
                int personalRating = rs.getInt("Personal_Rating");
                String filepath = rs.getString("FileLink");
                LocalDate lastviewed = rs.getDate("LastView").toLocalDate();
                int year = rs.getInt("Year");
                Movie movie = new Movie(id, movieTitle, imdbRating, personalRating, filepath, lastviewed, year);
                allMoviesInCategory.add(movie);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not get Category form database", ex);
        }
        return allMoviesInCategory;
    }
}
