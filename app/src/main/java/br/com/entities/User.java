package br.com.entities;

public class User {
    private String name;
    private String nameUser;
    private String password;
    private String email;
    private boolean isActive;
    private int win;
    private int loss;

    public User(String name, String password, String nameUser, String email) {
        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("O nome não pode ser vazio");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser vazio");
        }

        if (nameUser == null || nameUser.isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio");
        }

        this.name = name;
        this.password = password;
        this.email = email;
        this.nameUser = nameUser;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser vazio");
        }
        this.email = email;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {

        if (nameUser == null || nameUser.isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio");
        }
        this.nameUser = nameUser;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", nameUser='" + nameUser + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", win=" + win +
                ", loss=" + loss +
                '}';
    }
}
