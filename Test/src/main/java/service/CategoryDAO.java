package service;

import model.Category;
import myConnection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements InterfaceDAO<Category> {
    private final MyConnection myConnection = new MyConnection();

    private static final String INSERT_CATEGORY_SQL = "INSERT INTO category (CategoryName) VALUES (?);";
    private static final String UPDATE_CATEGORY_SQL = "update category set CategoryName = ? where id = ?;";
    private static final String SELECT_CATEGORY_BY_ID = "select CategoryName from category where id =?";
    private static final String SELECT_ALL_CATEGORY = "select * from category";
    private static final String DELETE_CATEGORY_SQL = "delete from category where id = ?;";
    @Override
    public void insert(Category category) throws SQLException {
        System.out.println(INSERT_CATEGORY_SQL);
        try (Connection connection = myConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {
            preparedStatement.setString(1, category.getCategoryName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Category select(int id) {
        Category category = null;
        try (Connection connection = myConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("CategoryName");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    @Override
    public List<Category> selectAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = myConnection.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("CategoryName");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = myConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Category category) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = myConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY_SQL);) {
            statement.setString(1, category.getCategoryName());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
