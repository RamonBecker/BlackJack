package br.com.entities;

import android.util.Log;

import com.example.Screens.GameActivity;

import java.util.ArrayList;
import java.util.List;

public class StoreUser {

    private static StoreUser storeUser;

    private List<User> listUser;

    private StoreUser() {
        this.listUser = new ArrayList<>();
        listUser.add(new User("admin", "admin", "admin", "admin"));
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
            if (user.getNameUser().contentEquals(userName.trim()) && user.getPassword().contentEquals(password.trim())) {
                user.setActive(true);
                userReturn = user;
            }
        }
        GameActivity.win = userReturn.getWin();
        GameActivity.loss = userReturn.getLoss();

        return userReturn;
    }

    public User updateWinLoss(User user, int win, int loss) {
        User userFound = null;
        for (User userSeach : listUser) {
            if (user.getName() == userSeach.getName() && user.getPassword() == userSeach.getPassword()) {
                userFound = userSeach;
                break;
            }
        }

        if (userFound != null) {
            userFound.setWin(win);
            userFound.setLoss(loss);
        }

        return userFound;
    }

    public User checkIsActive() {
        for (User user : listUser) {
            if (user.isActive()) {
                return user;
            }
        }
        return null;
    }

    public void logoffUser() {
        for (User user : listUser) {
            user.setActive(false);
        }
    }
}
