package school21.sockets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import school21.sockets.repositories.UsersRepository;
import school21.sockets.models.User;

import java.sql.SQLException;
import java.util.UUID;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String signUp(String login, String password) throws SQLException {
        User user = new User(login, passwordEncoder.encode(password));
        usersRepository.save(user);
        return password;
    }
}
