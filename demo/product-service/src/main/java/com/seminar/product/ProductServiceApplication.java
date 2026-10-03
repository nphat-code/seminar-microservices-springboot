package com.seminar.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/products")
public class ProductServiceApplication {

    private final Map<Long, Product> productDb = new HashMap<>();

    public ProductServiceApplication() {
        productDb.put(1L, new Product(1L, "Laptop Dell XPS 15", 35000000.0, 10));
        productDb.put(2L, new Product(2L, "iPhone 15 Pro Max", 29990000.0, 25));
        productDb.put(3L, new Product(3L, "Bàn phím cơ Keychron Q1", 4200000.0, 50));
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>(productDb.values());
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = productDb.get(id);
        if (product == null) {
            throw new RuntimeException("Sản phẩm không tồn tại với ID: " + id);
        }
        return product;
    }

    public static class Product {
        private Long id;
        private String name;
        private Double price;
        private Integer stock;

        public Product() {}

        public Product(Long id, String name, Double price, Integer stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
        public Integer getStock() { return stock; }
        public void setStock(Integer stock) { this.stock = stock; }
    }
}
