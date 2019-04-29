package com.example.hayri.dekorlink;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hayri.dekorlink.Fragments.ProfilFragment;
import com.example.hayri.dekorlink.Model.LoginModel;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    EditText email_input,password_input;
    TextView register;
    Button btnLogin;
    Vibrator v;

    //change this to match your url
    final String loginURL = "http://hayriyeberna.tk/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email_input = findViewById(R.id.email);
        password_input = findViewById(R.id.loginPassword);
        register = findViewById(R.id.register);
        btnLogin = findViewById(R.id.btnLogin);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserData();
            }
        });

        //when someone clicks on login
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    private void validateUserData() {

        //first getting the values
        final String email = email_input.getText().toString();
        final String password = password_input.getText().toString();

        //checking if username is empty
        if (TextUtils.isEmpty(email)) {
            email_input.setError("Lütfen email adresini giriniz");
            email_input.requestFocus();
            // Vibrate for 100 milliseconds
            v.vibrate(100);
            btnLogin.setEnabled(true);
            return;
        }
        //checking if password is empty
        if (TextUtils.isEmpty(password)) {
            password_input.setError("Lütfen şifrenizi giriniz");
            password_input.requestFocus();
            //Vibrate for 100 milliseconds
            v.vibrate(100);
            btnLogin.setEnabled(true);
            return;
        }



        //Login User if everything is fine
        loginUser(email,password);


    }

    private void loginUser(String email, String password) {

        //making api call
        Api api = ApiClient.getClient().create(Api.class);
        Call<LoginModel> login = api.login(email,password);

        login.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if(response.body().getIsSuccess() == 1){
                    //get username
                    String user = response.body().getUsername();
                    int userid=Integer.valueOf(response.body().getUyeID());


                    //storing the user in shared preferences
                    SharedPref.getInstance(MainActivity.this).storeUserName(user,userid);
                    Toast.makeText(MainActivity.this,response.body().getUsername(),Toast.LENGTH_LONG).show();

                    startActivity(new Intent(MainActivity.this,AnaActivity.class));
                }else{
                    Toast.makeText(MainActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                }




            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }

}
