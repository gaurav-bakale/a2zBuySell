package application;

import java.util.List;

public class ElectronicsProduct extends Product {
    private String brand;
    private String model;
    private String warrantyInfo;

    public ElectronicsProduct(String title, String description, double price, String category, List<String> images,
                              String brand, String model, String warrantyInfo) {
        super(title, description, price, category, images);
        this.brand = brand;
        this.model = model;
        this.warrantyInfo = warrantyInfo;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getWarrantyInfo() {
        return warrantyInfo;
    }

    @Override
    public String getProductDetails() {
        return "Brand: " + brand + "\nModel: " + model + "\nWarranty Info: " + warrantyInfo;
    }
}
