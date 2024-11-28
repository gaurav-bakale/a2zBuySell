package com.a2zbuysell.a2zbuysell;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    ArrayList<Product> products = new ArrayList<>();

    public void loadProducts() throws SQLException {

        DBManager dbm = new DBManager();
        List<List<Object>> res = dbm.executeQuery(
                """
                        select p.id, p.title, p.description, price, condition, c.name as category, s.name as subcategory
                        from products p
                        inner join categories c
                        on p.category_id = c.id
                        INNER  join subcategories s
                        on p.subcategory_id = s.id
                        """
        );

        for (List<Object> r : res){
            products.add(
                    new Product(
                            (Integer) r.get(0),
                            (String) r.get(1),
                            (String) r.get(2),
                            (Double) r.get(3),
                            (String) r.get(4),
                            (String) r.get(5),
                            (String) r.get(6)
                    )
            );
        }
    }



}
