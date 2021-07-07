package edu.school21.numbers.repositories;

import edu.school21.numbers.models.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{

    DataSource          dataSource;
    ResultSet           resultSet;
    PreparedStatement   preparedStatement;

    final String selectProductId =  "SELECT * FROM product.productTable WHERE productID = ";
    final String selectSaveProduct =  "INSERT INTO product.productTable VALUES (default, ";
    final String selectUpdateProduct =  "UPDATE product.productTable SET ";
    final String selectDeleteProduct =  "DELETE FROM product.productTable WHERE productID = ";


    private boolean retResultSet(String select) {
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Product> findById(Long id) {
        if (retResultSet(selectProductId + id)) {
            try {
                Product product = new Product(resultSet.getString(2),
                                                resultSet.getLong(1),
                                                resultSet.getLong(3));
                return Optional.of(product);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Long index = 0L;
        Optional<Product> optionalProduct;
        while ((optionalProduct = findById(++index)).isPresent()) {
            list.add(optionalProduct.get());
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    @Override
    public void update(Product product) {
        retResultSet(selectUpdateProduct + " WHERE productID = " + product.getUserId() + ", name = " + product.getName() + ", price = " + product.getPrice() + ')');
    }

    @Override
    public void save(Product product) {
        retResultSet(selectSaveProduct + product.getName() + ',' + product.getPrice() + " ) RETURNING id");
    }

    @Override
    public void delete(Long id) {
        retResultSet(selectDeleteProduct + id);
    }
}
