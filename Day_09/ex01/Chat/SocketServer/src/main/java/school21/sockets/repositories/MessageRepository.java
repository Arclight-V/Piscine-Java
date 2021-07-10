package school21.sockets.repositories;


import com.zaxxer.hikari.HikariDataSource;
import school21.sockets.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Component
public class MessageRepository implements CrudRepository<Message> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageRepository(HikariDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message findById(Long id) {
        return null;
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public void save(Message entity) throws SQLException {
        jdbcTemplate.update("INSERT INTO users.usertable VALUES (default, '%s', %d);", entity.getText(), entity.getAuthor().getIdentifier());
    }

    @Override
    public void update(Message entity) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}