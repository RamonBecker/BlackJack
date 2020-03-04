package br.com.entities;

public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }

        if (password == null || name.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        }
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || name.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        }
        this.password = password;
    }
}
