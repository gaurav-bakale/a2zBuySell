package com.a2zbuysell.a2zbuysell;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    ArrayList<Product> products = new ArrayList<>();
    DBManager dbm = new DBManager();

    public void loadProducts() throws SQLException {

        products.clear();

        List<List<Object>> res = dbm.executeQuery(
                """
                        select
                        	p.id,
                        	p.title,
                        	p.description,
                        	price,
                        	condition,
                        	c.name as category,
                        	s.name as subcategory,
                        	pi.image,
                        	u.username ,
                        	u.email ,
                        	u.phone_number,
                        	p.date_created
                        from
                        	products p
                        inner join categories c
                        on p.category_id = c.id
                        INNER join subcategories s
                        on p.subcategory_id = s.id
                        inner join product_images pi
                        on p.id = pi.product_id
                        inner JOIN users u
                        on p.user_id = u.id
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



}
