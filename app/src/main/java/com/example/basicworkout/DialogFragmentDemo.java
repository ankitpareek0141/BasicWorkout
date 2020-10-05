package com.example.basicworkout;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class DialogFragmentDemo extends DialogFragment {
    private EditText ed;
    private Spinner spinner;
    private Button ok, cancel;
    private Context context;
    private ArrayList<String> days;
    private GetData getData;
    private static final String TAG = "DialogFragmentDemo";

    public interface GetData{
        public void collectData(Plans plan);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: started");
        View view = getActivity().getLayoutInflater().inflate(R.layout.customized_dialog_box, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Want to Add")
                .setView(view);
        initViews(view);
        days = new ArrayList<>();
        days.add("Mon");
        days.add("Tue");
        days.add("Wed");
        days.add("Thu");
        days.add("Fri");
        days.add("Sat");
        days.add("Sun");
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, days);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: called");
                //Toast.makeText(getContext(), days.get(position)+" clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        try {
            getData = (GetData) getActivity();                         // create interfse instance
        }catch(ClassCastException exp){exp.printStackTrace(); }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: OK dialog clicked");
                Bundle bundle = getArguments();
                MyGym gymObject = bundle.getParcelable("incomingGym");
                if(null!=gymObject){
                    Plans plan = new Plans();
                    plan.setIsAccomplished(false);
                    plan.setDays(days.get(spinner.getSelectedItemPosition()));
                    plan.setMinutes(Integer.parseInt(ed.getText().toString()));
                    plan.setGym(gymObject);
                    Utils.addUsersPlan(plan);
                    getData.collectData(plan);
                }
            }
        });
        return builder.create();
    }

    public void initViews(View view){
        ed = view.findViewById(R.id.minutes);
        spinner = view.findViewById(R.id.spinner);
        ok = view.findViewById(R.id.ok);
        cancel = view.findViewById(R.id.cancel);
    }
}
