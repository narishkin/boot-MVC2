package home.catechumen.bootMVC.service;

import home.catechumen.bootMVC.dao.RoleDao;
import home.catechumen.bootMVC.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAll(){
        return roleDao.getAll();
    }

}
