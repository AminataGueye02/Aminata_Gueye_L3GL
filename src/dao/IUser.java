package dao;

import entity.User;

import java.util.List;

public interface IUser {
    public int addUser();
    public List<User> listUsers();
}
