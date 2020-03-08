package br.com.entities;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StoreUser {

    private static StoreUser storeUser;

    private List<User> listUser;

    private StoreUser() {
        this.listUser = new ArrayList<>();
    }

    public static StoreUser getInstance() {
        if (storeUser == null) {
            storeUser = new StoreUser();
        }
        return storeUser;
    }

    public List<User> getListUser() {
        if (this.listUser == null) {
            this.listUser = new ArrayList<>();
        }
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        if (listUser == null) {
            throw new IllegalArgumentException("Voce não pode passar uma lista vazia");
        }
        this.listUser = listUser;
    }

    public void addUser(User user) {
        listUser.add(user);
        Log.i("teste", listUser.toString());
    }

    public boolean login(String userName, String password) {

        if (listUser == null) {
            return false;
        }

        for (User user : listUser) {
            if (user.getNameUser().equals(userName.trim()) && user.getPassword().equals(password.trim())) {

                return true;
            }
        }
        return false;
    }

    public User checkLoginUserActive(String userName, String password) {
        User userReturn = null;
        for (User user : listUser) {
            if (user.getNameUser().equals(userName.trim()) && user.getPassword().equals(password.trim())) {
                user.setActive(true);
                userReturn = user;
            }
        }
        return userReturn;
    }

    public User checkIsActive() {
        for (User user : listUser) {
            if (user.isActive()) {
                return user;
            }
        }
        return null;
    }
}
