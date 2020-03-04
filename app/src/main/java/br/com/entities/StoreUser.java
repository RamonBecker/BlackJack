package br.com.entities;

import java.util.ArrayList;
import java.util.List;

public class StoreUser {

    private List<User> listUser;

    public StoreUser() {
        this.listUser = new ArrayList<>();
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
}
