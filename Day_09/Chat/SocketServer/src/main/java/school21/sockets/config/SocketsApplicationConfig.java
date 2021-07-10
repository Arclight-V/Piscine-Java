package school21.sockets.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import school21.sockets.repositories.UsersRepository;
import school21.sockets.repositories.UsersRepositoryJdbcImpl;


@Configuration
@ComponentScan("school21.sockets")
@PropertySource("classpath:db.properties")

public class SocketsApplicationConfig {


    @Value("spring.datasource.url")
    private String url;

    @Autowired
    private Environment env;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(env.getProperty("spring.datasource.username"));
        hikariConfig.setPassword(env.getProperty("spring.datasource.password"));
        hikariConfig.setUsername(env.getProperty("spring.datasource.username"));
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
    public UsersRepository usersRepositoryJdbcTemplate(HikariDataSource hikariDataSource) {
        return new UsersRepositoryJdbcImpl(hikariDataSource);
    }

}
