package edu.school21.numbers.repositories;

import edu.school21.numbers.models.Product;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ProductsReposutoryJdbcImpl implements ProductsRepository{

    DataSource dataSource;

    public ProductsReposutoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }
}
