package com.example.basicworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btnOne, btnNew, btnThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");
        btnOne = findViewById(R.id.seeAllPlans);
        btnNew = findViewById(R.id.dailyWorkout);
        btnThree = findViewById(R.id.aboutUs);
        Utils utils = new Utils();
        btnNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: MainActivity started btbNew");
                Intent intent = new Intent(MainActivity.this, AllExercise.class);
                startActivity(intent);
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: MainActivity started btnOne");
                Intent intent = new Intent(MainActivity.this, PlanActivity.class);
                startActivity(intent);
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutDialog dialog = new AboutDialog();
                dialog.show(getSupportFragmentManager(), "About dialog");
            }
        });
    }
}