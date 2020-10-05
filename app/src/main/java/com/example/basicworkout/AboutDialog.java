package com.example.basicworkout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AboutDialog extends DialogFragment {

    private Button button;
    private TextView text;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.about_layout, null);
        text = view.findViewById(R.id.text);
        button = view.findViewById(R.id.button);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("About Us")
                .setView(view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return builder.create();
    }
}
