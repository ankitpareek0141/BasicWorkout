package com.example.basicworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.util.Util;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlanActivity extends AppCompatActivity{

    private RecyclerView monRecView, tueRecView, wedRecView, thuRecView, friRecView, satRecView, sunRecView;
    private TextView monEdit, tueEdit, wedEdit, thuEdit, friEdit, satEdit, sunEdit;
    private RelativeLayout noPlanAddLayout;
    private Button addPlan;
    private NestedScrollView scrollView;
    private PlanRecViewAdapter monAdapter, tueAdapter, wedAdapter, thuAdapter, friAdapter , satAdapter, sunAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_recycler_view);

        initViews();
        initAdapter();
        initRecView();

        if(Utils.getUsersPlan().size()>0){
            scrollView.setVisibility(View.VISIBLE);
            noPlanAddLayout.setVisibility(View.GONE);
        }
        else{
            scrollView.setVisibility(View.GONE);
            noPlanAddLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }

    private void initAdapter(){
        monAdapter = new PlanRecViewAdapter(this);
        ArrayList<Plans> monPlan = new ArrayList<>();
        for(Plans plan : Utils.getUsersPlan()){
            if(plan.getDays().equals("Mon"))
                monPlan.add(plan);
        }
        monAdapter.setPlanList(monPlan);

        tueAdapter = new PlanRecViewAdapter(this);
        ArrayList<Plans> tuePlan = new ArrayList<>();
        for(Plans plan :Utils.getUsersPlan()){
            if(plan.getDays().equals("Tue"))
                tuePlan.add(plan);
        }
        tueAdapter.setPlanList(tuePlan);

        wedAdapter = new PlanRecViewAdapter(this);
        ArrayList<Plans> wedPlan = new ArrayList<>();
        for(Plans plan : Utils.getUsersPlan()){
            if(plan.getDays().equals("Wed"))
                wedPlan.add(plan);
        }
        wedAdapter.setPlanList(wedPlan);

        thuAdapter = new PlanRecViewAdapter(this);
        ArrayList<Plans> thuPlan = new ArrayList<>();
        for(Plans plan : Utils.getUsersPlan()){
            if(plan.getDays().equals("Thu"))
                thuPlan.add(plan);
        }
        thuAdapter.setPlanList(thuPlan);

        friAdapter = new PlanRecViewAdapter(this);
        ArrayList<Plans> friPlan = new ArrayList<>();
        for(Plans plan : Utils.getUsersPlan()){
            if(plan.getDays().equals("Fri"))
                friPlan.add(plan);
        }
        friAdapter.setPlanList(friPlan);

        satAdapter = new PlanRecViewAdapter(this);
        ArrayList<Plans> satPlan = new ArrayList<>();
        for(Plans plan : Utils.getUsersPlan()){
            if(plan.getDays().equals("Sat"))
                satPlan.add(plan);
        }
        satAdapter.setPlanList(satPlan);

        sunAdapter = new PlanRecViewAdapter(this);
        ArrayList<Plans> sunPlan = new ArrayList<>();
        for(Plans plan : Utils.getUsersPlan()){
            if(plan.getDays().equals("Sun"))
                sunPlan.add(plan);
        }
        sunAdapter.setPlanList(sunPlan);
    }

    public void initRecView(){
        monRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        monRecView.setAdapter(monAdapter);
        tueRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        tueRecView.setAdapter(tueAdapter);
        wedRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        wedRecView.setAdapter(wedAdapter);
        thuRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        thuRecView.setAdapter(thuAdapter);
        friRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        friRecView.setAdapter(friAdapter);
        satRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        satRecView.setAdapter(satAdapter);
        sunRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        sunRecView.setAdapter(sunAdapter);
    }

    private void initViews(){
        monRecView = findViewById(R.id.recViewMonday);
        tueRecView = findViewById(R.id.recViewTuesday);
        wedRecView = findViewById(R.id.recViewWednesday);
        thuRecView = findViewById(R.id.recViewThursday);
        friRecView = findViewById(R.id.recViewFriday);
        satRecView = findViewById(R.id.recViewSaturday);
        sunRecView = findViewById(R.id.recViewSunday);

        monEdit = findViewById(R.id.mondayEdit);
        tueEdit = findViewById(R.id.tuesdayEdit);
        wedEdit = findViewById(R.id.wednesdayEdit);
        thuEdit = findViewById(R.id.thursdayEdit);
        friEdit = findViewById(R.id.fridayEdit);
        satEdit = findViewById(R.id.saturdayEdit);
        sunEdit = findViewById(R.id.sundayEdit);

        noPlanAddLayout = findViewById(R.id.noPlanAddedRelLayout);
        scrollView = findViewById(R.id.nestedcScroll);
        addPlan = findViewById(R.id.addButton);
    }

    public void onClick(View view) {
        Intent intent = new Intent(PlanActivity.this, EditActivity.class);
        switch(view.getId()){
            case R.id.mondayEdit: intent.putExtra("Plan Day", "Mon");
                startActivity(intent);
                break;
            case R.id.tuesdayEdit: intent.putExtra("Plan Day", "Tue");
                startActivity(intent);
                break;
            case R.id.wednesdayEdit: intent.putExtra("Plan Day", "Wed");
                startActivity(intent);
                break;
            case R.id.thursdayEdit: intent.putExtra("Plan Day", "Thu");
                startActivity(intent);
                break;
            case R.id.fridayEdit: intent.putExtra("Plan Day", "Fri");
                startActivity(intent);
                break;
            case R.id.saturdayEdit: intent.putExtra("Plan Day", "Sat");
                startActivity(intent);
                break;
            case R.id.sundayEdit: intent.putExtra("Plan Day", "Sun");
                startActivity(intent);
                break;
            default:startActivity(intent);
                        break;
        }
    }
    public void emptyFun(View view) {
        Intent intent = new Intent(PlanActivity.this, AllExercise.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}