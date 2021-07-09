package edu.school21.models;

import java.util.Objects;

public class Product {
    String name;
    Long userId;
    Long price;

    public Product(String name, Long userId, Long price) {
        this.name = name;
        this.userId = userId;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) && userId.equals(product.userId) && price.equals(product.price);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userId, price);
    }

    public Product clone() {
        return new Product(this.name, this.userId, this.price);
    }
}
