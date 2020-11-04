package com.nabil.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText editNum;
    private ExampleDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view)
                .setTitle("Edit")
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .setPositiveButton("OK", (dialog, which) -> {
                    String editNumber = editNum.getText().toString();
                    if (editNumber.equals("")) {
                        Log.d("TAG","null");
                    } else {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.counter = Integer.parseInt(editNumber);
                        listener.applyNum(editNumber);
                    }
                });

        editNum = view.findViewById(R.id.editNum);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialog Listener");
        }
    }

    public interface ExampleDialogListener {
        void applyNum(String editNumber);
    }
}
