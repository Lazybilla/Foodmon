package com.example.fooduck;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.Adpters.RecyclerviewAdapter;
import com.example.model.Food;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class DonorActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private DatabaseReference mref;
    private List<Food> list;
    private RecyclerviewAdapter adapter;
    private FloatingActionButton fab;

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


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DonorActivity.this);
                // ...Irrelevant code for customizing the buttons and title
                LayoutInflater inflater = DonorActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.foodo, null);
                dialogBuilder.setView(dialogView);

                EditText name = dialogView.findViewById(R.id.food_name);
                EditText quantity = dialogView.findViewById(R.id.quantity);
                ImageView iamge = dialogView.findViewById(R.id.food_image);
                dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(DonorActivity.this,"Save",Toast.LENGTH_LONG).show();



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
}
