package edu.school21.numbers.repositories;

import edu.school21.numbers.models.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductsReposutoryJdbcImpl implements ProductsRepository{

    DataSource          dataSource;
    ResultSet           resultSet;
    PreparedStatement   preparedStatement;

    String selectProductId =  "SELECT * FROM chat.user WHERE userID = ";

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

    public ProductsReposutoryJdbcImpl(DataSource dataSource) {
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
