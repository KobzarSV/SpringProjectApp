package ua.goit.model;

public enum UserRole {
    ROLE_VISITOR("ROLE_VISITOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
