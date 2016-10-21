package com.viro.staff.ui.custom.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.viro.staff.R;

import java.util.Calendar;

public class YearPickerDialog extends DialogFragment {

    public static final int YEAR_MIN_DIFF = 18;
    private static final int YEAR_MAX_DIFF = 80;

    private DatePickerDialog.OnDateSetListener listener;

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Calendar cal = Calendar.getInstance();

        View dialog = inflater.inflate(R.layout.dialog_year_picker, null);
        final NumberPicker yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        int year = cal.get(Calendar.YEAR);
        yearPicker.setMinValue(year - YEAR_MAX_DIFF);
        yearPicker.setMaxValue(year);
        yearPicker.setValue(year - YEAR_MIN_DIFF);

        builder.setView(dialog)
                // Add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDateSet(null, yearPicker.getValue(), 0, 0);
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        YearPickerDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
