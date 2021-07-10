package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{

    private JdbcTemplate jdbcTemplate;

    final String selectUserId =  "SELECT * FROM users.usertable WHERE userid = ";
    final String selectAll =  "SELECT * FROM users.usertable";
    final String UpdateUser =  "UPDATE users.usertable SET ";
    final String DeleteUser =  "DELETE FROM users.usertable WHERE userid = ";
    final String selectUserEmail =  "SELECT * FROM users.usertable WHERE email = ";
    
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
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
        jdbcTemplate.update(String.format("INSERT INTO users.userTable VALUES ('%d', %s);", entity.getIdentifier(), entity.getEmail()));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(UpdateUser + "email = " + '\'' + entity.getEmail() + '\'' + " WHERE userid = " + entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DeleteUser + id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(jdbcTemplate.queryForObject(selectUserEmail + email, new Object[]{email}, new BeanPropertyRowMapper<>(User.class)));
    }
}
