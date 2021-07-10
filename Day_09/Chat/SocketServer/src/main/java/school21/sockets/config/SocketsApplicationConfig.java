package school21.sockets.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import school21.sockets.repositories.UsersRepository;
import school21.sockets.repositories.UsersRepositoryJdbcImpl;
import school21.sockets.server.Server;


@Configuration
@ComponentScan("school21.sockets")
@PropertySource("classpath:db.properties")

public class SocketsApplicationConfig {

    @Autowired
    private Environment env;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(env.getProperty("db.url"));
        hikariConfig.setUsername(env.getProperty("db.user"));
        hikariConfig.setPassword(env.getProperty("db.password"));
        hikariConfig.setUsername(env.getProperty("db.driver.name"));
        return  hikariConfig;
    }

    @Bean
    public HikariDataSource hikariDataSource(HikariConfig hikariConfig) {
        return  new HikariDataSource(hikariConfig);
    }

    @Bean
    public UsersRepository usersRepositoryJdbc(HikariDataSource hikariDataSource) {
        return new UsersRepositoryJdbcImpl(hikariDataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Server server() {
        return new Server();
    }

}
