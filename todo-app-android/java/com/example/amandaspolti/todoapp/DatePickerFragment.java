package com.example.amandaspolti.todoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by AmandaSpolti on 10/12/2016.
 */
public class DatePickerFragment extends DialogFragment {


    DatePickerDialog.OnDateSetListener v;

    public DatePickerFragment(){};

    public DatePickerFragment(DatePickerDialog.OnDateSetListener v){
        this.v =v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it Bundle b = getArguments();
        return new DatePickerDialog(getActivity(), v, year,
                month, day);
    }

}



