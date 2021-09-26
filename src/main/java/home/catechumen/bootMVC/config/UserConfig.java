package home.catechumen.bootMVC.config;

import home.catechumen.bootMVC.dao.UserDao;
import home.catechumen.bootMVC.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;


@Configuration
public class UserConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(UserDao userDao){
//        return args -> {
//            User user = new User("kot","123",true, LocalDate.of(1977, Month.AUGUST,22));
//            userDao.save(user);
//        };
//    }
}
