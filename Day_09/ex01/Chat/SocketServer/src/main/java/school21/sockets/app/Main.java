package school21.sockets.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.sockets.repositories.UsersRepository;
import school21.sockets.config.SocketsApplicationConfig;
import school21.sockets.server.Server;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        Server server = context.getBean(Server.class);
        server.start(8081);
    }
}
