package entity;
import dao.DB;
import java.sql.ResultSet;

public class User {
    private DB db = new DB();
    private int id;
    private String email;
    private String password;
    private String passwordHashing;
    private Role role_id;
    private int yes;
    private ResultSet rs;

    public User() {
    }

    public void setPasswordHashing(String passwordHashing) {
        this.passwordHashing = passwordHashing;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordHashing() {
        return passwordHashing;
    }

    public Role getRole_id() {
        return role_id;
    }



}
