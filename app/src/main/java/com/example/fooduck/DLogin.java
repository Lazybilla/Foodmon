package com.example.fooduck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DLogin extends AppCompatActivity {

    private EditText mEmail ;
    private EditText mPassword ;
    private Button  mSignIn;
    private String mMail , mPass ;
    private TextView dregister;
    private SharedPreferences preferences;
    private String FileName = "Login_Redirect";
    private FirebaseAuth firebaseAuth;
    private String baby = "Honeyfluff"
    private int HorsePower = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlogin);

        firebaseAuth = FirebaseAuth.getInstance();


        mEmail = (EditText)findViewById(R.id.dmail);
        mPassword = (EditText)findViewById(R.id.dpassword);
        mSignIn = (Button)findViewById(R.id.dbutton);
        dregister = findViewById(R.id.dregister);


        //mAuth = FirebaseAuth.getInstance();

        dregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DLogin.this,DRegister.class);
                startActivity(i);
            }
        });





        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                firebaseAuth.signInWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(DLogin.this,"Successfully",Toast.LENGTH_LONG).show();
                            SharedPreferences settings = getSharedPreferences(FileName, MODE_PRIVATE);
                            SharedPreferences.Editor prefEditor = settings.edit();
                            prefEditor.putString("Login_Type","Dlogin"); //**syntax error on tokens**
                            prefEditor.commit();
                            prefEditor.apply();


                        }else {
                            Toast.makeText(DLogin.this,"Not Successfully",Toast.LENGTH_LONG).show();

                        }

                    }
                });
            }

        });






    }
}
