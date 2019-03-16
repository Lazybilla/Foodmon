package com.example.fooduck;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button ngoopen,dopen;
    private FirebaseAuth mAuth;
    private String FileName = "Login_Redirect";
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(FileName, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        type = settings.getString("Login_Type","none");

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null && type.equals("NGOLogin")){
            finish();
            startActivity(new Intent(MainActivity.this,Restaurant_Finder.class));
        }else if (mAuth.getCurrentUser()!=null && type.equals("Dlogin")){

            finish();
            startActivity(new Intent(MainActivity.this,DonorActivity.class));

        }

        ngoopen = findViewById(R.id.ngoopen);
        dopen = findViewById(R.id.dopen);

        ngoopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NGOLogin.class);
                startActivity(intent);

            }
        });

        dopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,DLogin.class);
                startActivity(intent);


            }
        });

    }

}
