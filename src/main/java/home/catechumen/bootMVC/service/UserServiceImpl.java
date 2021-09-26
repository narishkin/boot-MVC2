package home.catechumen.bootMVC.service;

import home.catechumen.bootMVC.dao.UserDao;
import home.catechumen.bootMVC.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl{

    UserDao userDao;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder=passwordEncoder;
    }

    public List<User> getAll(){
        return userDao.findAll();
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    public void delete(Long id){
        userDao.deleteById(id);
    }

    public User getById(Long id){
        return userDao.getById(id);
    }

    public void update(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }


}
