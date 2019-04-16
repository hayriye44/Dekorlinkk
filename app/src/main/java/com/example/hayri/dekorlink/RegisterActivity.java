package com.example.hayri.dekorlink;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hayri.dekorlink.Model.LoginModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {
    EditText username,email,password,TelNo;
    TextView login;
    Button btnRegister;
    Vibrator v;
    //change to your register url
    final String registerUrl = "http://hayriyeberna.tk/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.registerName);
        email = (EditText) findViewById(R.id.registerEmail);
        password = (EditText) findViewById(R.id.registerPassword);
        TelNo = (EditText) findViewById(R.id.telefon);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        login = (TextView) findViewById(R.id.login);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserData();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void validateUserData() {

        //find values
        final String reg_name = username.getText().toString();
        final String reg_email = email.getText().toString();
        final String reg_password = password.getText().toString();
        final String reg_tel = TelNo.getText().toString();


//        checking if username is empty
        if (TextUtils.isEmpty(reg_name)) {
            username.setError("Lütfen Ad Soyad giriniz");
            username.requestFocus();
            // Vibrate for 100 milliseconds
            v.vibrate(100);
            return;
        }
        //checking if email is empty
        if (TextUtils.isEmpty(reg_email)) {
            email.setError("Lütfen email giriniz");
            email.requestFocus();
            // Vibrate for 100 milliseconds
            v.vibrate(100);
            return;
        }
        //checking if password is empty
        if (TextUtils.isEmpty(reg_password)) {
            password.setError("Lütfen şifrenizi giriniz");
            password.requestFocus();
            //Vibrate for 100 milliseconds
            v.vibrate(100);
            return;
        }
        //validating email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(reg_email).matches()) {
            email.setError("Lütfen geçeri email giriniz");
            email.requestFocus();
            //Vibrate for 100 milliseconds
            v.vibrate(100);
            return;
        }

        //After Validating we register User
        registerUser(reg_name,reg_email,reg_password,reg_tel);

    }

    private void registerUser(String user_name, String user_mail, String user_pass,String user_tel) {


        final String reg_username = username.getText().toString();
        final String reg_email = email.getText().toString();
        final String reg_password = password.getText().toString();
        final String reg_tel = TelNo.getText().toString();


        //call retrofit
        //making api call
        Api api = ApiClient.getClient().create(Api.class);
        Call<LoginModel> login = api.register(user_name,user_mail,user_pass,user_tel);

        login.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if(response.body().getIsSuccess() == 1){
                    //get username
                    String user = response.body().getUsername();

                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                }else{
                    Toast.makeText(RegisterActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });





    }
}
