package ra.model;

import java.util.Set;

public class User {
    private int id;
    private String name;

    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private boolean status;
    private Set<Roll> rolls;

    public User() {
    }

    public User(int id, String name, String username, String password, String address, String phoneNumber, boolean status, Set<Roll> rolls) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.rolls = rolls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Roll> getRolls() {
        return rolls;
    }

    public void setRolls(Set<Roll> rolls) {
        this.rolls = rolls;
    }
}
