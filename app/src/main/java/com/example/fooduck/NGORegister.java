package com.example.fooduck;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NGORegister extends AppCompatActivity {

    private static final int GET_FROM_GALLERY1 = 1234;
    private static final int GET_FROM_GALLERY2 = 12345;
    private static final int GET_FROM_GALLERY3 = 12346;

    private EditText mNgoName ,mNgoID, mAadharUri,mGovtDoctUri,mLetterUri,mPhoneNumber,mEmail,mPassword,mRepassword ;
    private Button mGovDoctButton,mAadharButton,mLetterbtn,mRegister;
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
        mLetterbtn = (Button)findViewById(R.id.letter_btn);

        mRegister = (Button)findViewById(R.id.register);





        // Buttons
        mGovDoctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY2);

            }
        });

        mAadharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY1);


            }
        });

        mLetterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY3);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY1 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage1 = data.getData();
            mAadharUri.setText(String.valueOf(selectedImage1));

            }
            if(requestCode==GET_FROM_GALLERY2 && resultCode == Activity.RESULT_OK) {
                Uri selectedImage2 = data.getData();
                mGovtDoctUri.setText(String.valueOf(selectedImage2));
                }

                if(requestCode==GET_FROM_GALLERY3 && resultCode == Activity.RESULT_OK) {
                    Uri selectedImage3 = data.getData();
                    mLetterUri.setText(String.valueOf(selectedImage3));
                    }

        }



}
