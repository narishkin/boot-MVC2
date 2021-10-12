package home.catechumen.bootMVC.controller;

import java.util.List;


public class UserRolesDTO {

    private List<String> rolesIds;

    public UserRolesDTO() {
    }

    public UserRolesDTO(List<String> rolesIds) {
        this.rolesIds = rolesIds;
    }

    public List<String> getRolesIds() {
        return rolesIds;
    }

    public void setRolesIds(List<String> rolesIds) {
        this.rolesIds = rolesIds;
    }

    @Override
    public String toString() {
        return "UserRolesDTO{" + "rolesIds=" + rolesIds +'}';
    }
}
