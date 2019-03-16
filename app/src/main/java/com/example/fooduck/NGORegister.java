package com.example.fooduck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NGORegister extends AppCompatActivity {

    private EditText mNgoName ,mNgoID, mAadharUri,mGovtDoctUri,mLetterUri,mPhoneNumber,mEmail,mPassword,mRepassword ;
    private Button mGovDoctButton,mAadharButton,mRegister;
    private String mName,mID,mPhone,mMail,mPass,mRepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoregister);

        // EditText
        mNgoName = (EditText)findViewById(R.id.ngo_name);
        mNgoID = (EditText)findViewById(R.id.ngo_rid);
        mAadharUri = (EditText)findViewById(R.id.aadhar_img);
        mGovtDoctUri = (EditText)findViewById(R.id.govt_doc);
        mLetterUri = (EditText)findViewById(R.id.letter);
        mPhoneNumber = (EditText)findViewById(R.id.Phone_Number);
        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText)findViewById(R.id.password);
        mRepassword = (EditText)findViewById(R.id.retype);

        // Button
        mGovDoctButton = (Button)findViewById(R.id.govt_doc_btn);
        mAadharButton = (Button)findViewById(R.id.aadhar_img_btn);
        mRegister = (Button)findViewById(R.id.register);






        // Buttons
        mGovDoctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mAadharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mName = mNgoName.getText().toString();
                mID = mNgoID.getText().toString();
                mPhone = mPhoneNumber.getText().toString();
                mMail = mEmail.getText().toString();
                mPass = mPassword.getText().toString();
                mRepass = mRepassword.getText().toString();










            }
        });




    }
}
