package com.example.fooduck;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.model.DonateR;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.List;

public class DRegister extends AppCompatActivity {

    private Switch aSwitch;
    private LinearLayout restaurant,individual;
    private EditText email,password;
    private FirebaseAuth mAuth;
    private Button register;
    private EditText mName,mAddress,mDescription,mRailway,mEmail,mPassword,mRetype,mLicense,mWebsite,mPhoneNumber;
    private String   Name , Address , Description , Railway , Email, Password , Retype ,License , Website ,Phone_no;
    private DatabaseReference mref;
    private Spinner spinner;
    private ProgressDialog dialog;
    private String FileName = "Login_Redirect";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dregister);

        aSwitch = findViewById(R.id.switch1);
        restaurant = findViewById(R.id.restaurant);
        individual = findViewById(R.id.individual);
        mAuth = FirebaseAuth.getInstance();


        // Define
        mName = (EditText)findViewById(R.id.Rname);
        mAddress = (EditText)findViewById(R.id.address);
        mDescription = (EditText)findViewById(R.id.Desc);
        mRailway = (EditText)findViewById(R.id.station);
        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText)findViewById(R.id.password);
        mRetype = (EditText)findViewById(R.id.retype);
        mWebsite = (EditText)findViewById(R.id.website);
        mLicense = (EditText)findViewById(R.id.RLicence);
        mPhoneNumber = findViewById(R.id.Phone_Number);
        spinner = findViewById(R.id.spinner);
        dialog = new ProgressDialog(DRegister.this);
        dialog.setMessage("Please wait while Regitering");
        dialog.setTitle("Please wait");
        dialog.setCanceledOnTouchOutside(false);
        mref = FirebaseDatabase.getInstance().getReference();


        List<String> list = new ArrayList<String>();
        list.add("Veg");
        list.add("Non-Veg");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        if (!aSwitch.isChecked()){

            individual.setVisibility(View.GONE);
            register = findViewById(R.id.register);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.show();
                    Name = mName.getText().toString() ;
                    Address = mAddress.getText().toString();
                    Description = mDescription.getText().toString();
                    Railway = mRailway.getText().toString();
                    Email = mEmail.getText().toString();
                    Password = mPassword.getText().toString();
                    //Retype = mRetype.getText().toString();
                    License = mLicense.getText().toString();
                    Website = mWebsite.getText().toString();
                    Phone_no = mPhoneNumber.getText().toString();

                    final DonateR donateR = new DonateR();
                    donateR.setRName(Name);
                    donateR.setAddr(Address);
                    donateR.setDesc(Description);
                    donateR.setN_Railway(Railway);
                    donateR.setEmail(Email);
                    donateR.setRlicenc_No(License);
                    donateR.setWebsite(Website);
                    donateR.setPhone_no(Phone_no);


                    mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                donateR.setUID(UID);
                                mref.child("Donor Info").child("Restaurant").child(Name).setValue(donateR).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(DRegister.this,"Successfully",Toast.LENGTH_LONG).show();
                                        finish();
                                        SharedPreferences settings = getSharedPreferences(FileName, MODE_PRIVATE);
                                        SharedPreferences.Editor prefEditor = settings.edit();
                                        prefEditor.putString("Login_Type","Dlogin"); //**syntax error on tokens**
                                        prefEditor.commit();
                                        prefEditor.apply();
                                        finish();
                                        Intent i = new Intent(DRegister.this,DonorActivity.class);
                                        startActivity(i);
                                        dialog.dismiss();
                                    }
                                });

                            }else {
                                dialog.dismiss();
                                Toast.makeText(DRegister.this,"Not Successfully",Toast.LENGTH_LONG).show();

                            }
                        }
                    });



                }
            });

        }else if (aSwitch.isChecked()){

            individual.setVisibility(View.VISIBLE);
            restaurant.setVisibility(View.GONE);
            Individual();
        }



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
                    Restaurant();
                }

            }
        });

    }

    private void Restaurant() {


    }

    private void Individual() {



    }
}
