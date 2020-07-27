package com.arthurpaiva96.reminderapp.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arthurpaiva96.reminderapp.dao.ReminderDAO;
import com.arthurpaiva96.reminderapp.model.Reminder;
import com.arthurpaiva96.reminderapp.ui.activity.ReminderFormActivity;
import com.arthurpaiva96.reminderapp.ui.activity.ReminderListActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.TOAST_AFTER_ADD_REMINDER;

public class ReminderFormView {

    private final Context context;

    public ReminderFormView(Context context){
        this.context = context;
    }

    public void configureTimePicker(EditText reminderHourInput) {
        final TimePickerDialog.OnTimeSetListener
                actionsAfterChooseTime = setHourAfterChoose(reminderHourInput);

        reminderHourInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(context,
                        actionsAfterChooseTime, 0, 0, true)
                        .show();
            }
        });
    }

    public void configureDatePicker(EditText reminderDateInput) {

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener
                actionsAfterChooseDate = setDateAfterChoose(calendar, reminderDateInput);

        reminderDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context,
                        actionsAfterChooseDate,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener setDateAfterChoose(final Calendar calendar,
                                                                  final EditText reminderDateInput) {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                reminderDateInput.setText(new SimpleDateFormat(
                        "dd/MM/yyyy", new Locale("pt","BR"))
                        .format(calendar.getTime()));

            }
        };
    }



    private TimePickerDialog.OnTimeSetListener setHourAfterChoose(final EditText reminderHourInput) {
        return new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                reminderHourInput.setText(String.format(Locale.getDefault(),"%02d:%02d",hourOfDay,minute));
            }
        };
    }

    public void saveButtonBehavior(Button saveReminderButton,
                                   final Reminder reminderToBeSaved,
                                   final EditText reminderTitleInput,
                                   final EditText reminderDescriptionInput,
                                   final EditText reminderDateInput,
                                   final EditText reminderHourInput) {

        saveReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUserInputAsReminder(reminderToBeSaved, reminderTitleInput,
                        reminderDescriptionInput, reminderDateInput, reminderHourInput);

                Toast.makeText(context, TOAST_AFTER_ADD_REMINDER,
                        Toast.LENGTH_LONG).show();

                context.startActivity(new Intent(context, ReminderListActivity.class));

                ((Activity) context).finish();

            }
        });

    }

    private void saveUserInputAsReminder(Reminder reminderToBeSaved,
                                         EditText reminderTitleInput,
                                         EditText reminderDescriptionInput,
                                         EditText reminderDateInput,
                                         EditText reminderHourInput) {

        String reminderTitle = reminderTitleInput.getText().toString();
        String reminderDescription = reminderDescriptionInput.getText().toString();
        String reminderDate = reminderDateInput.getText().toString();
        String reminderHour = reminderHourInput.getText().toString();

        reminderToBeSaved.setTitle(reminderTitle);
        reminderToBeSaved.setDescription(reminderDescription);
        reminderToBeSaved.setDate(reminderDate);
        reminderToBeSaved.setHour(reminderHour);

        new ReminderDAO().editReminder(reminderToBeSaved);

    }

}
