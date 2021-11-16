package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    TextInputEditText etName, etEmail, etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = findViewById(R.id.btnRegister);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        onSignUpClicked();
    }

    public void onSignUpClicked() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, username, password, email;
                name = String.valueOf(etName.getText());
                username = String.valueOf(etUsername.getText());
                password = String.valueOf(etPassword.getText());
                email = String.valueOf(etEmail.getText());

                //When there is text in all fields, then execute:
                if (!name.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) { 
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = name;
                            field[1] = username;
                            field[2] = password;
                            field[3] = email;
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = "name";
                            data[1] = "username";
                            data[2] = "password";
                            data[3] = "email";
                            PutData putData = new PutData("http://192.168.1.129/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")) {
                                      Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                      startActivity(intent);
                                      finish();
                                    }
                                    //End ProgressBar (Set visibility to GONE)
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else {
                    //When there is no text in the edit text fields, show toast
                    Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        //Removes animation when switching to loginActivity
        overridePendingTransition(0,0);
    }
}
