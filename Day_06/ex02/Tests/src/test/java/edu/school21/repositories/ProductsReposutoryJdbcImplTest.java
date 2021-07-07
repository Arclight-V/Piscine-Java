package edu.school21.repositories;

import edu.school21.numbers.models.Product;
import edu.school21.numbers.repositories.ProductsRepository;
import edu.school21.numbers.repositories.ProductsReposutoryJdbcImpl;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

public class ProductsReposutoryJdbcImplTest {

    DataSource dataSource;
    ProductsRepository repository;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS =
            Arrays.asList(
                    new Product("Product1", 123L),
                    new Product("Product2", 321L),
                    new Product("Product3", 456L),
                    new Product("Product4", 654L),
                    new Product("Product5", 789L)
            );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(2);
    final Product EXPECTED_UPDATED_PRODUCT = new Product("Product4", 4L , 999L);
    final Product EXPECTED_SAVE_PRODUCT = new Product("Product6", 1000L);

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataSource = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        repository = new ProductsReposutoryJdbcImpl(dataSource);
    }

    @Test
    void findAllTest() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    void findByIdTest() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getUserId()));
    }

    @Test
    void updateTest() {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getUserId()));
    }

    @Test
    void saveTest() {
        repository.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, repository.findById(EXPECTED_SAVE_PRODUCT.getUserId()));
    }

    @Test
    void deleteTest() {
        repository.delete(EXPECTED_FIND_ALL_PRODUCTS.get(2).getUserId());
        Assertions.assertNull(repository.findById(EXPECTED_FIND_ALL_PRODUCTS.get(2).getUserId()));
    }
}
