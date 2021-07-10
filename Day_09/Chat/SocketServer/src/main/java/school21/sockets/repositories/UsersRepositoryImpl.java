package school21.sockets.repositories;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school21.sockets.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


@Component
public class UsersRepositoryImpl implements UsersRepository{

    private JdbcTemplate jdbcTemplate;

    final String selectUserId =  "SELECT * FROM users.usertable WHERE userid = ";
    final String selectAll =  "SELECT * FROM users.usertable";
    final String UpdateUser =  "UPDATE users.usertable SET ";
    final String DeleteUser =  "DELETE FROM users.usertable WHERE userid = ";
    final String selectUserName =  "SELECT * FROM users.usertable WHERE name = ";

    @Autowired
    public UsersRepositoryImpl(HikariDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {

        return jdbcTemplate.queryForObject(selectUserId + id, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findAll() {
        List<User> list = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(User.class));
        return list;
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(String.format("INSERT INTO users.usertable VALUES ('%d', %s);", entity.getIdentifier(), entity.getName()));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(UpdateUser + "name = " + '\'' + entity.getName() + '\'' + " WHERE userid = " + entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DeleteUser + id);
    }

    @Override
    public Optional<User> findByEmail(String name) {
        return Optional.of(jdbcTemplate.queryForObject(selectUserName + name, new Object[]{name}, new BeanPropertyRowMapper<>(User.class)));
    }
}
