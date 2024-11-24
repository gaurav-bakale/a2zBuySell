package application;

import java.util.List;

public class FurnitureProduct extends Product {
    private String material;
    private String dimensions;
    private String color;

    public FurnitureProduct(String title, String description, double price, String category, List<String> images,
                            String material, String dimensions, String color) {
        super(title, description, price, category, images);
        this.material = material;
        this.dimensions = dimensions;
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String getProductDetails() {
        return "Material: " + material + "\nDimensions: " + dimensions + "\nColor: " + color;
    }
}
