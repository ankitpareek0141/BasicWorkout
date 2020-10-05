package com.example.basicworkout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WorkoutDetail extends AppCompatActivity implements DialogFragmentDemo.GetData {

    private Button addButton;
    private TextView name;
    private TextView details;
    private ImageView imageView;
    private String day;
    private int min;
    private MyGym incomingGym;
    private static final String TAG = "WorkoutDetail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);
        initWidgets();
        try {
            Intent intent = getIntent();
            incomingGym = intent.getParcelableExtra("Details");
            name.setText(incomingGym.getName());
            details.setText(incomingGym.getDescription());
            Glide.with(this)
                    .asBitmap()
                    .load(incomingGym.getImageURL())
                    .into(imageView);
        }
        catch (NullPointerException e){
            Log.d(TAG, "onCreate: NULLPOINTEREXCEPTION occured in RecViewAdapter of GYM");
        }
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                    DialogFragmentDemo fragmentDemo = new DialogFragmentDemo();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("incomingGym", incomingGym);
                    fragmentDemo.setArguments(bundle);
                    fragmentDemo.show(getSupportFragmentManager(), "myDialog");
            }
        });
    }

    private void initWidgets(){
        addButton = findViewById(R.id.addButton);
        name = findViewById(R.id.workOutName);
        details = findViewById(R.id.details);
        imageView = findViewById(R.id.imageView);
    }

    @Override
    public void collectData(Plans plans) {
        Intent intent = new Intent(this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("GYMObject", plans);
        startActivity(intent);
    }
}