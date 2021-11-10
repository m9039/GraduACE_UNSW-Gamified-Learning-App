package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnRegister);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

    }

    public void goToRegister(View view){
        Intent intent = new Intent (this, RegisterActivity.class);
        startActivity(intent);
        //Removes animation when switching to registerActivity
        overridePendingTransition(0,0);
    }

    public void goToHome(View view){
        Intent intent = new Intent (this, HomeActivity.class);
        startActivity(intent);
    }
}