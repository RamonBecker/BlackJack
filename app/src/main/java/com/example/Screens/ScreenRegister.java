package com.example.Screens;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.entities.StoreUser;
import br.com.entities.User;

public class ScreenRegister extends AppCompatActivity {

    EditText txtUser;
    EditText txtPassword;
    EditText txtEmail;
    EditText txtNameUser;
    List<EditText> listEditText = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        txtUser = (EditText) findViewById(R.id.editTextRegisterUser);
        txtPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        txtEmail = (EditText) findViewById(R.id.editTextRegisterEmail);
        txtNameUser = (EditText) findViewById(R.id.editTextRegisterNameUser);

        listEditText.add(txtUser);
        listEditText.add(txtPassword);
        listEditText.add(txtEmail);
        listEditText.add(txtNameUser);
    }

    public void registerUser(View v) {

        StoreUser storeUser = StoreUser.getInstance();
        String user = txtUser.getText().toString();
        String nameUser = txtNameUser.getText().toString();
        String email = txtEmail.getText().toString();
        String pass = txtPassword.getText().toString();

        try {

            User userCreate = new User(user, pass, nameUser, email);
            storeUser.addUser(userCreate);

            txtUser.setText("");
            txtPassword.setText("");
            txtNameUser.setText("");
            txtEmail.setText("");
            finish();

        } catch (Exception e) {

            for (EditText editText : listEditText) {
                if (editText.getText().toString().isEmpty()) {
                    editText.setBackgroundTintList(ColorStateList.valueOf(-65536));
                }
            }


            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}
