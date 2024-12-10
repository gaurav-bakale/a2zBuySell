package com.a2zbuysell.a2zbuysell;

import java.sql.SQLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

interface IProductManager {
    // Method to load products from the database
    void loadProducts() throws SQLException;
}

public class ProductManager implements IProductManager {

    public ArrayList<Product> products = new ArrayList<>();
    private DBManager dbm = new DBManager();

    @Override
    public void loadProducts() throws SQLException {
        products.clear();

        List<List<Object>> res = dbm.executeQuery(
                """
                        SELECT
                            p.id,
                            p.title,
                            p.description,
                            price,
                            condition,
                            c.name AS category,
                            s.name AS subcategory,
                            pi.image,
                            u.username,
                            u.email,
                            u.phone_number,
                            p.date_created
                        FROM
                            products p
                        INNER JOIN categories c
                            ON p.category_id = c.id
                        INNER JOIN subcategories s
                            ON p.subcategory_id = s.id
                        INNER JOIN product_images pi
                            ON p.id = pi.product_id
                        INNER JOIN users u
                            ON p.user_id = u.id
                        ORDER BY p.date_created DESC
                        """
        );

        for (List<Object> r : res) {
            products.add(
                    new Product(
                            (Integer) r.get(0),
                            (String) r.get(1),
                            (String) r.get(2),
                            (Double) r.get(3),
                            (String) r.get(4),
                            (String) r.get(5),
                            (String) r.get(6),
                            (byte[]) r.get(7),
                            (String) r.get(8),
                            (String) r.get(9),
                            (String) r.get(10),
                            (String) r.get(11)
                    )
            );
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}