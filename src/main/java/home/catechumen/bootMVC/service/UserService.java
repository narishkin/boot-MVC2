package home.catechumen.bootMVC.service;

import home.catechumen.bootMVC.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getById(Long id);
    void save(User user);
    void update(User updatedUser);
    void delete(Long id);

}
