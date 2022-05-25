package ua.goit.model.dto;

import ua.goit.model.dao.RoleDao;

import java.util.UUID;

public class UsersDto {

    private UUID id;
    private String email;
    private String password;
    private String name;
    private RoleDao roleDao;

    public UsersDto(UUID id, String email, String password, String name, RoleDao roleDao) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleDao = roleDao;
    }

    public UsersDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
