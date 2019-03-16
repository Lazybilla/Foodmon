package com.example.fooduck;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.Adpters.RecyclerviewAdapter;
import com.example.model.Food;
import com.example.model.NGO_model;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class DonorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference mref;
    private List<Food> list;
    private RecyclerviewAdapter adapter;
    private FloatingActionButton fab;
    private String name,quantity,image;
    private static final int GET_FROM_GALLERY1 = 1234;
    private  ImageView edtiamge;
    private StorageReference mStorageRef;
    private Uri selectedImage1;
    private  String type,UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        fab = findViewById(R.id.floatingActionButton);
        LinearLayoutManager lm =new LinearLayoutManager(DonorActivity.this);
        recyclerView.setLayoutManager(lm);
        adapter = new RecyclerviewAdapter(list);
        recyclerView.setAdapter(adapter);
        mref = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mref.child("Food").child("Restaurante").child(UID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Food food = dataSnapshot.getValue(Food.class);
                    list.add(food);
                    adapter = new RecyclerviewAdapter(list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DonorActivity.this);
                LayoutInflater inflater = DonorActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.foodo, null);
                dialogBuilder.setView(dialogView);

                final EditText edtname = dialogView.findViewById(R.id.food_name);
                final EditText edtquantity = dialogView.findViewById(R.id.quantity);
                edtiamge = dialogView.findViewById(R.id.food_image);
                Spinner spinner = dialogView.findViewById(R.id.spinner);
                final List<String> list = new ArrayList<String>();
                list.add("Veg");
                list.add("Non-Veg");
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DonorActivity.this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        type = list.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                edtiamge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY1);

                    }
                });
                dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {

                        Toast.makeText(DonorActivity.this,"Save",Toast.LENGTH_LONG).show();
                        name = edtname.getText().toString();
                        quantity = edtquantity.getText().toString();
                        String fileName = getFileName(selectedImage1);
                        final StorageReference fileToUpload = FirebaseStorage.getInstance().getReference().child("Images").child(fileName);

                        UploadTask uploadTask =  fileToUpload.putFile(selectedImage1);
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
                                    String imageUrl = String.valueOf(downloadUri);
                                    Food food = new Food();
                                    food.setFoodname(name);
                                    food.setNo_of_people(quantity);
                                    food.setImage_url(imageUrl);
                                    food.setVeg(type);

                                    mref.child("Food").child("Restaurante").child(UID).push().setValue(food).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(DonorActivity.this,"Sucessfully added",Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }
                                    });


                                } else {
                                    Toast.makeText(DonorActivity.this,"Somethng went wrong",Toast.LENGTH_LONG).show();
                                    // Handle failures
                                    // ...
                                }
                            }
                        });

                    }
                });
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();


            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY1 && resultCode == Activity.RESULT_OK) {
            selectedImage1 = data.getData();
            edtiamge.setImageURI(selectedImage1);
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
