package school21.sockets.services;

import java.sql.SQLException;

public interface UsersService {
    String signUp(String email) throws SQLException;
}
