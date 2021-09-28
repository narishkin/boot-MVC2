package home.catechumen.bootMVC.config.init;

import home.catechumen.bootMVC.dao.UserDao;
import home.catechumen.bootMVC.model.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;


@Configuration
public class ApplicationRunnerImpl implements ApplicationRunner{

    private final UserDao userDao;

    public ApplicationRunnerImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        User user = new User("kot","123",true, LocalDate.of(1977, Month.AUGUST,22));
//        userDao.save(user);
    }
}
