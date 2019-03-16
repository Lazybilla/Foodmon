package com.example.fooduck;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.NGO_model;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class NGORegister extends AppCompatActivity {

    private static final int GET_FROM_GALLERY1 = 1234;
    private static final int GET_FROM_GALLERY2 = 12345;
    private static final int GET_FROM_GALLERY3 = 12346;

    private EditText mNgoName ,mNgoID, mAadharUri,mGovtDoctUri,mLetterUri,mPhoneNumber,mEmail,mPassword,mRepassword ;
    private Button mGovDoctButton,mAadharButton,mLetterbtn,mRegister;
    private String mName,mID,mPhone,mMail,mPass,mRepass;
    private ArrayList<Uri> list;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    private ArrayList<String> imglist;
    private FirebaseAuth mAuth;
    private DatabaseReference mref;
    private ProgressDialog dialog;
    private String FileName = "Login_Redirect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoregister);

        mAuth = FirebaseAuth.getInstance();
        mref = FirebaseDatabase.getInstance().getReference();

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
        dialog = new ProgressDialog(NGORegister.this);
        dialog.setMessage("Please wait while registering");
        dialog.setTitle("Please wait");
        dialog.setCanceledOnTouchOutside(false);

        // Button
        mGovDoctButton = (Button)findViewById(R.id.govt_doc_btn);
        mAadharButton = (Button)findViewById(R.id.aadhar_img_btn);
        mLetterbtn = (Button)findViewById(R.id.letter_btn);

        mRegister = (Button)findViewById(R.id.register);

        list = new ArrayList<>();
        imglist = new ArrayList<>();


        mStorageRef = FirebaseStorage.getInstance().getReference();



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

                dialog.show();
                mName = mNgoName.getText().toString();
                mID = mNgoID.getText().toString();
                mPhone = mPhoneNumber.getText().toString();
                mMail = mEmail.getText().toString();
                mPass = mPassword.getText().toString();
                mRepass = mRepassword.getText().toString();
                for (int i = 0; i<=2; i++){

                    String fileName = getFileName(list.get(i));
                    final StorageReference fileToUpload = FirebaseStorage.getInstance().getReference().child("Images").child(fileName);

                    UploadTask uploadTask =  fileToUpload.putFile(list.get(i));
                    final int finalI = i;
                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }


                            // Continue with the task to get the download URL
                            return fileToUpload.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                imglist.add(String.valueOf(downloadUri));
                                String a = String.valueOf(downloadUri);

                                if (finalI==2){

                                    final NGO_model model = new NGO_model();
                                    model.setNgo_name(mName);
                                    model.setNgo_reg(mID);
                                    model.setPhone_no(mPhone);
                                    model.setEmail(mMail);
                                    model.setAadhar_img_url(imglist.get(0));
                                    model.setGovt_ltr_img_url(imglist.get(1));
                                    model.setJoing_ltr_img_ltr(imglist.get(2));
                                    mAuth.createUserWithEmailAndPassword(mMail,mPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {

                                            String auth = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                            model.setUID(auth);
                                            mref.child("NGO Info").child(mName).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                    SharedPreferences settings = getSharedPreferences(FileName, MODE_PRIVATE);
                                                    SharedPreferences.Editor prefEditor = settings.edit();
                                                    prefEditor.putString("Login_Type","NGOLogin"); //**syntax error on tokens**
                                                    prefEditor.commit();
                                                    prefEditor.apply();
                                                    Toast.makeText(NGORegister.this,"sucessfully",Toast.LENGTH_LONG).show();
                                                    dialog.dismiss();
                                                    finish();
                                                    Intent i = new Intent(NGORegister.this,Restaurant_Finder.class);
                                                    startActivity(i);

                                                }
                                            });

                                        }
                                    });




                                }


                            } else {
                                Toast.makeText(NGORegister.this,"Somethng went wrong",Toast.LENGTH_LONG).show();
                                // Handle failures
                                // ...
                            }
                        }
                    });





                }


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
            list.add(0,selectedImage1);

            }
            if(requestCode==GET_FROM_GALLERY2 && resultCode == Activity.RESULT_OK) {
                Uri selectedImage2 = data.getData();
                mGovtDoctUri.setText(String.valueOf(selectedImage2));
                list.add(1,selectedImage2);

            }

                if(requestCode==GET_FROM_GALLERY3 && resultCode == Activity.RESULT_OK) {
                    Uri selectedImage3 = data.getData();
                    mLetterUri.setText(String.valueOf(selectedImage3));
                    list.add(2,selectedImage3);


                }

        }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }



}
