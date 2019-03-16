package com.example.fooduck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NGOLogin extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button mSignIn;
    private String mMail , mPass ;
    private FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngologin);


        mEmail = (EditText)findViewById(R.id.ngomail);
        mPassword = (EditText)findViewById(R.id.ngopassword);
        mSignIn = (Button)findViewById(R.id.ngobutton);

        mAuth = FirebaseAuth.getInstance();




        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMail = mEmail.getText().toString();
                mPass = mPassword.getText().toString();


                if(!mMail.isEmpty() && !mPass.isEmpty())
                {
                    mAuth.signInWithEmailAndPassword(mMail,mPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {




                        }
                    });
                }

            }
        });

    }



}