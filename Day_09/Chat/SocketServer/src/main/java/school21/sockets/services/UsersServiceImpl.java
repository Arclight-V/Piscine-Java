package school21.sockets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.sockets.repositories.UsersRepository;
import school21.sockets.models.User;

import java.sql.SQLException;
import java.util.UUID;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    @Qualifier("usersRepositoryJdbc")
    UsersRepository usersRepository;

    @Override
    public String signUp(String email) throws SQLException {
        String password = UUID.randomUUID().toString();
        User user = new User(email, password);
        usersRepository.save(user);
        return password;
    }
}
