package DAL.db;

import BE.Category;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
public class CategoryDAO_DB implements ICategoryDataAccess{
    private DataBaseConnecter dataBaseConnecter;

    public List<Category> allCategorys() throws Exception{
        ArrayList<Category> allCategory = new ArrayList<>();

        try (Connection conn = dataBaseConnecter.getConnection())
        {
            String sql ="SELECT * FROM PlayList;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Id");
                String categoryname = rs.getString("Name");

                Category category = new Category(id,categoryname);
                category.setMovies(movieCategoryDAO.getMovieCategory(category));
                allCategory.add(category);
            }
            return allCategory;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get Category form database", ex);
        }
    }







*/


