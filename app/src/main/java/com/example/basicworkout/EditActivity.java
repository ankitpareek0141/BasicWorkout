package com.example.basicworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements PlanRecViewAdapter.OnDeletePlan{

    private Button addMore;
    private TextView planDay;
    private RecyclerView recyclerView;
    private PlanRecViewAdapter adapter;
    private static final String TAG = "EditActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: EditActivity started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initViews();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setType("Edit");
        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();
        try{
            String day = intent.getStringExtra("Plan Day");
            if(day!=null)
                planDay.setText(day);
            ArrayList<Plans> editPlans = new ArrayList<>();
            for(Plans plan : Utils.getUsersPlan()){
                if(plan.getDays().equals(day)){
                    editPlans.add(plan);
                }
            }
            adapter.setPlanList(editPlans);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, AllExercise.class);
                startActivity(intent);
            }
        });
    }
    public void initViews(){
        planDay = findViewById(R.id.txtDay);
        addMore = findViewById(R.id.addMorePlan);
        recyclerView = findViewById(R.id.editRecView);
        adapter = new PlanRecViewAdapter(this);
    }

    @Override
    public void deletePlan(String day) {
        Log.d(TAG, "deletePlan: started");
        planDay.setText(day);
        ArrayList<Plans> plans = new ArrayList<>();
        for(Plans plan : Utils.getUsersPlan()){
            if(plan.getDays().equals(day))
                plans.add(plan);
        }
        adapter.setPlanList(plans);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: started");
        Intent intent = new Intent(this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}