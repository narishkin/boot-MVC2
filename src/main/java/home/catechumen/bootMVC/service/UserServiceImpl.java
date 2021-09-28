package home.catechumen.bootMVC.service;

import home.catechumen.bootMVC.dao.UserDao;
import home.catechumen.bootMVC.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional (readOnly = true)
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional (readOnly = true)
    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);
    }
}
