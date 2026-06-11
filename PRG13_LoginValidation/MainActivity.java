package com.example.loginappvalidation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputUsername;
    private EditText inputPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin();
            }
        });
    }

    private void validateLogin() {

        String username =
                inputUsername.getText()
                        .toString()
                        .trim();

        String password =
                inputPassword.getText()
                        .toString()
                        .trim();

        if (TextUtils.isEmpty(username)
                || TextUtils.isEmpty(password)) {

            Toast.makeText(
                    this,
                    "Username and Password cannot be empty",
                    Toast.LENGTH_SHORT
            ).show();

        } else {

            if (username.equals("admin")
                    && password.equals("1234")) {

                Toast.makeText(
                        this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Toast.makeText(
                        this,
                        "Login Fail",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}