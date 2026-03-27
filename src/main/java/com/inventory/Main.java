package com.inventory;

import com.inventory.dao.impl.CategoryDAOImpl;
import com.inventory.dao.impl.ProductDAOImpl;
import com.inventory.model.Category;
import com.inventory.model.Product;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Inventory Management System ===");

        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        ProductDAOImpl productDAO = new ProductDAOImpl();

        // 1. Add Category
        Category category = new Category("Electronics");
        boolean catAdded = categoryDAO.addCategory(category);
        System.out.println("Category Added: " + catAdded);

        // 2. Get Category
        System.out.println("All Categories:");
        categoryDAO.getAllCategories().forEach(System.out::println);

        // 3. Add Product
        Product product = new Product(
                "Laptop",
                "SKU-101",
                50000.0,
                10,
                "Gaming Laptop",
                null,
                null,
                1
        );

        boolean prodAdded = productDAO.addProduct(product);
        System.out.println("Product Added: " + prodAdded);

        // 4. Get Products
        System.out.println("\nAll Products:");
        productDAO.getAllProducts().forEach(System.out::println);

        System.out.println("\n=== Done ===");
    }
}