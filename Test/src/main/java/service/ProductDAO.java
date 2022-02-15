package service;

import model.Category;
import model.Product;
import myConnection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final MyConnection myConnection = new MyConnection();

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (ProductName, Price, Quantity, Color, Category) VALUES (?, ?, ?, ? ,?);";
    private static final String SELECT_PRODUCT_BY_ID = "select idproduct,ProductName,Price,Quantity,Color,Category from product where id =?";
    private static final String SELECT_ALL_PRODUCT = "select * from product";
    private static final String UPDATE_PRODUCT_SQL = "update product set ProductName = ?,Price= ?, Quantity =?, Color=?, Category=? where id = ?;";
    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?;";
    private static final String SEARCH_PRODUCT_SQL = "SELECT product.idproduct,product.ProductName,product.Price,product.Quantity,product.Color,product.Category category.CategoryName from product \n +"+
                                                        "JOIN category c ON c.id = product.Category\n" +
                                                        "WHERE product.name like ?  ";


    public void insert(Product product, int idCategory) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        try (Connection connection = myConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setInt(5, idCategory);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void edit(Product product, int idCategory) throws SQLException{
        System.out.println(INSERT_PRODUCT_SQL);
        try (Connection connection = myConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setInt(5, idCategory);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Product select(int id) {
        Product product = null;
        try (Connection connection = myConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("ProductName");
                Double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String color = rs.getString("Color");
                String category = rs.getString("Category");
                product = new Product(id, name, price, quantity,color,category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }


    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = myConnection.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idproduct");
                String name = rs.getString("ProductName");
                Double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String color = rs.getString("Color");
                String category = rs.getString("Category");
                products.add(new Product(id, name,price,quantity,color,category));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }


    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = myConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }


    public boolean update(Product product, int idCategory) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = myConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setInt(5, idCategory);
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<Product> searchByName(String key) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = myConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_SQL)) {
            preparedStatement.setString(1, key);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idproduct");
                String name = rs.getString("ProductName");
                Double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                String color = rs.getString("Color");
                String category = rs.getString("Category");
                products.add(new Product(id, name,price,quantity,color,category));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
}
