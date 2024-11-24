package application;

import java.util.List;

public abstract class Product {
    private String title;
    private String description;
    private double price;
    private String category;
    private List<String> images;

    public Product(String title, String description, double price, String category, List<String> images) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getImages() {
        return images;
    }

    public abstract String getProductDetails();
}
