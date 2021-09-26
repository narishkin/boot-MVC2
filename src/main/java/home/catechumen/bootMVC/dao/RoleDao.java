package home.catechumen.bootMVC.dao;

import home.catechumen.bootMVC.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

}
