package school21.sockets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import school21.sockets.models.Message;
import school21.sockets.repositories.MessageRepository;
import school21.sockets.repositories.UsersRepository;
import school21.sockets.models.User;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public String signUp(String login, String password) throws SQLException {
        User user = new User(login, passwordEncoder.encode(password));
        usersRepository.save(user);
        return password;
    }

    @Override
    public boolean signIn(String login, String password) throws SQLException {
        Optional<User> user  = usersRepository.findByEmail(login);
        if (user.isPresent() == true) {
            if (passwordEncoder.matches(password, user.get().getPassword()))
                return true;
        }
        return false;
    }

    @Override
    public void sendMessage(String username, String text) throws SQLException {
        messageRepository.save(new Message(null, usersRepository.findByEmail(username).get(), text, null));
    }
}
