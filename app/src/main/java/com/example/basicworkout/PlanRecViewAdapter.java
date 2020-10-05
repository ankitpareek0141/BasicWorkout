package com.example.basicworkout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlanRecViewAdapter extends RecyclerView.Adapter<PlanRecViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Plans> planList = new ArrayList<>();
    private String type = "";
    interface OnDeletePlan{
        public void deletePlan(String day);
    }
    private OnDeletePlan interfaceObject;

    public PlanRecViewAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.workoutName.setText(planList.get(position).getGym().getName());
        holder.minutes.setText(planList.get(position).getMinutes()+" minutes");
        Glide.with(context)
                .asBitmap()
                .load(planList.get(position).getGym().getImageURL())
                .into(holder.imageView);
        if(planList.get(position).getIsAccomplished()){
            holder.desabled.setVisibility(View.GONE);
            holder.enabled.setVisibility(View.VISIBLE);
        }
        else{
            holder.enabled.setVisibility(View.GONE);
            holder.desabled.setVisibility(View.VISIBLE);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(context, WorkoutDetail.class);
              intent.putExtra("Details", planList.get(position).getGym());
              context.startActivity(intent);
            }
        });
        try{
        interfaceObject = (OnDeletePlan) context;
        }catch(ClassCastException e){
            e.printStackTrace();
        }
        if(type.equals("Edit")){
            holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Want to Remove?");
                    builder.setMessage("Do you want to remove "+planList.get(position).getGym().getName()+" from "+planList.get(position).getDays());
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utils.removeUsersPlan(planList.get(position));
                            interfaceObject.deletePlan(planList.get(position).getDays());
                            notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                    return true;
                }
            });

            holder.desabled.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Accomplished?");
                    builder.setMessage("Do you want to accomplish this plan");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            planList.get(position).setIsAccomplished(true);
                            for(Plans plan : Utils.getUsersPlan()){
                                if(plan.equals(planList.get(position))){
                                    plan.setIsAccomplished(true);
                                }
                            }
                            notifyDataSetChanged();
                        }
                    });
                    builder.create().show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return planList.size();
    }

    public void setPlanList(ArrayList<Plans> planList){           // passing planList planList
        this.planList =  planList;
    }

    public void setType(String type){
        this.type = type;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView workoutName;
        private ImageView imageView;
        private TextView minutes;
        private ImageView enabled, desabled;
        private CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutName = itemView.findViewById(R.id.workoutName);
            imageView = itemView.findViewById(R.id.imageView);
            minutes = itemView.findViewById(R.id.minutes);
            enabled = itemView.findViewById(R.id.enabled);
            desabled = itemView.findViewById(R.id.desabled);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}