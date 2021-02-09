package com.example.larntech;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText edEmail ,edName, edPassword;//Переменные для ввода текста.
    Button btnSignUp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edEmail = findViewById(R.id.edLogin);
        edName = findViewById(R.id.edNickName);//Поле ввода логина
        edPassword = findViewById(R.id.edPassword);
        btnSignUp = findViewById(R.id.signUp_btn);//Кнопка "Зарегистрироваться"

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setEmail(edEmail.getText().toString());
                registerRequest.setUsername(edName.getText().toString());
                registerRequest.setPassword(edPassword.getText().toString());
                registerUser(registerRequest);
            }
        });
    }

    public void registerUser(RegisterRequest registerRequest)
    {
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful())
                {
                    String message = "Регистрация успешна";
                    Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_LONG ).show();

                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }
                else
                {
                    String message = "Регистрация невозможна - Ошибка";
                    Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_LONG ).show();
            }
        });
    }
}