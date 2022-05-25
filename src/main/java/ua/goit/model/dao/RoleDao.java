package ua.goit.model.dao;

import java.util.UUID;

public class RoleDao {

    private UUID id;
    private String name;

    public RoleDao(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDao() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
