package edu.school21.repositories;

import edu.school21.models.Product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {

    DataSource dataSource;
    ProductsRepository repository;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS =
            Arrays.asList(
                    new Product( "Product1", 1L, 123L),
                    new Product("Product2", 2L,321L),
                    new Product("Product3", 3L,456L),
                    new Product("Product4", 4L,654L),
                    new Product("Product5", 5L,789L)
            );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(2);
    final Product EXPECTED_UPDATED_PRODUCT = new Product("Product4", 4L , 999L);
    final Product EXPECTED_SAVE_PRODUCT = new Product("Product6", 6L, 1000L);

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataSource = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        repository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findAllTest() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    public void findByIdTest() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(EXPECTED_FIND_BY_ID_PRODUCT.getUserId()).get());
    }

    @Test
    public void updateTest() {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getUserId()).get());
    }

    @Test
    public void saveTest() {
        repository.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, repository.findById(EXPECTED_SAVE_PRODUCT.getUserId()).get());
    }

    @Test
    public void deleteTest() {
        repository.delete(EXPECTED_FIND_ALL_PRODUCTS.get(2).getUserId());
        Assertions.assertFalse(repository.findById(EXPECTED_FIND_ALL_PRODUCTS.get(2).getUserId()).isPresent());
    }
}
