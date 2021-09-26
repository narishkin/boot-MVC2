package home.catechumen.bootMVC.dao;

import home.catechumen.bootMVC.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query(value = "select u FROM User u where u.name=?1")
    User findUserByName(String name);
}
