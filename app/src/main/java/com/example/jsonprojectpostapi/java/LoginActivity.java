package com.example.jsonprojectpostapi.java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jsonprojectpostapi.MainActivity;
import com.example.jsonprojectpostapi.R;

public class LoginActivity extends AppCompatActivity {
    EditText name, phoneNo, email, password, confirmPassword;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name= findViewById(R.id.etName);
        phoneNo= findViewById(R.id.etPhoneNo);
        email= findViewById(R.id.etEmailAdress);
        password= findViewById(R.id.etPassword);
        confirmPassword= findViewById(R.id.etConfirmPassword);
        btnNext= findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validation();

            }
        });
    }

    private void validation() {
        if (name.length() == 0){
            name.setError("This field must required");
        }
        else if (phoneNo.length() == 0) {
            phoneNo.setError("This field must required");
        }
        else if (email.length() == 0) {
            email.setError("This field must required");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Enter valid email pattern");


        }
        else if (password.length()==0) {
            password.setError("This field must required");
        }
        else if (password.length()<8) {
            password.setError("Password must be minimum 8 characters");
        }
        else if ( confirmPassword.length()== 0) {
            confirmPassword.setError("This field must required");
        }
        else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("password and confirm password must be same");
        }
        else {
            Intent intent= new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }


    }
}