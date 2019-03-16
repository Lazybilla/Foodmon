package com.example.fooduck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class DRegister extends AppCompatActivity {

    private Switch aSwitch;
    private LinearLayout restaurant,individual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dregister);

        aSwitch = findViewById(R.id.switch1);
        restaurant = findViewById(R.id.restaurant);
        individual = findViewById(R.id.individual);

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
