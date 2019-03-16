package com.example.fooduck;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DRegister extends AppCompatActivity {

    private Switch aSwitch;
    private LinearLayout restaurant,individual;
    private EditText email,password;
    private FirebaseAuth mAuth;
    private Button register;
    private EditText mName,mAddress,mDescription,mRailway,mEmail,mPassword,mRetype,mLicense,mWebsite;
    private String   Name , Address , Description , Railway , Email, Password , Retype ,License , Website ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dregister);

        aSwitch = findViewById(R.id.switch1);
        restaurant = findViewById(R.id.restaurant);
        individual = findViewById(R.id.individual);
        mAuth = FirebaseAuth.getInstance();

        if (!aSwitch.isChecked()){
            email = findViewById(R.id.email);
            password = findViewById(R.id.password);
            register = findViewById(R.id.register);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(DRegister.this,"Successfully",Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(DRegister.this,"Not Successfully",Toast.LENGTH_LONG).show();


                            }
                        }
                    });


                }
            });
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = mName.getText().toString() ;
                Address = mAddress.getText().toString();
                Description = mDescription.getText().toString();
                Railway = mRailway.getText().toString();
                Email = mEmail.getText().toString();
                Password = mPassword.getText().toString();
                Retype = mRetype.getText().toString();
                License = mLicense.getText().toString();
                Website = mWebsite.getText().toString();
                Retype = mRetype.getText().toString();





            }
        });



        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    individual.setVisibility(View.VISIBLE);
                    restaurant.setVisibility(View.GONE);
                    Individual();

                }else {

                    individual.setVisibility(View.GONE);
                    restaurant.setVisibility(View.VISIBLE);

                }

            }
        });

    }

    private void Individual() {


    }
}
