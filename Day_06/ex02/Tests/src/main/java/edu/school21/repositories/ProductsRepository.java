package edu.school21.repositories;

import edu.school21.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void update(Product product);
    void save(Product product);
    void delete (Long id);
}
