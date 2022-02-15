package model;

public class Product {
    private int id;
    private String productName;
    private double price;
    private int quantity;
    private String color;
    private String category;

    public Product(int id, String productName, double price, int quantity, String color, String category) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.category = category;
    }

    public Product(String productName, double price, int quantity, String color, String category) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.category = category;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
