package com.example.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.entities.StoreUser;
import br.com.entities.User;

public class LoginActivity extends AppCompatActivity {

    EditText txtUserName;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtUserName = (EditText) findViewById(R.id.txtLoginNameUser);
        txtPassword = (EditText) findViewById(R.id.editTextLoginPassword);

      //  StoreUser storeUser = StoreUser.getInstance();

      //  storeUser.addUser(new User("admin", "admin", "admin", "admin"));
    }


    public void actionScreenRegisterUser(View v) {
        Intent it = new Intent(this, ScreenRegister.class);
        startActivity(it);
        finish();
    }

    public void actionButtonLogin(View view) {
        StoreUser storeUser = StoreUser.getInstance();


        //storeUser.addUser(new User("admin", "admin", "admin", "admin"));
        String nameUser = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();

        //Log.i("teste", "USER:"+nameUser+" PASS"+password);

        if (storeUser.login(txtUserName.getText().toString(), txtPassword.getText().toString())) {

            storeUser.checkLoginUserActive(txtUserName.getText().toString(), txtPassword.getText().toString());
            Intent it = new Intent(this, GameActivity.class);
            startActivity(it);
            finish();

        } else {
            Toast.makeText(this, R.string.usuario_senha_invalidos, Toast.LENGTH_LONG).show();
        }
    }
}
