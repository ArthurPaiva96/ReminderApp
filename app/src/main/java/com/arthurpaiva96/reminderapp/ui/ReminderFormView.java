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

import com.arthurpaiva96.reminderapp.broadcast.ReminderAlarmManager;
import com.arthurpaiva96.reminderapp.database.ReminderDatabase;
import com.arthurpaiva96.reminderapp.database.dao.ReminderDAO;
import com.arthurpaiva96.reminderapp.model.Reminder;
import com.arthurpaiva96.reminderapp.ui.activity.ReminderListActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.DEFAULT_ID;
import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.TOAST_AFTER_ADD_REMINDER;
import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.TOAST_REMINDER_FORM_FIELD_CANT_BE_NULL;

public class ReminderFormView {

    private final Context context;

    public ReminderFormView(Context context) {
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
                        "dd/MM/yyyy", new Locale("pt", "BR"))
                        .format(calendar.getTime()));

            }
        };
    }


    private TimePickerDialog.OnTimeSetListener setHourAfterChoose(final EditText reminderHourInput) {
        return new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                reminderHourInput.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
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

                boolean savedSuccessfully = saveUserInputAsReminder(reminderToBeSaved, reminderTitleInput,
                        reminderDescriptionInput, reminderDateInput, reminderHourInput);

                if(savedSuccessfully) {
                    Toast.makeText(context, TOAST_AFTER_ADD_REMINDER,
                            Toast.LENGTH_LONG).show();

                    context.startActivity(new Intent(context, ReminderListActivity.class));

                    ((Activity) context).finish();

                }else {
                    Toast.makeText(context, TOAST_REMINDER_FORM_FIELD_CANT_BE_NULL,
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private boolean saveUserInputAsReminder(Reminder reminderToBeSaved,
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

        if(reminderToBeSaved.allFieldsAreNotNull()) {
            this.saveReminder(reminderToBeSaved);
            return true;
        }
        return false;
    }

    private void saveReminder(Reminder reminderToBeSaved) {

        ReminderDatabase reminderDatabase = ReminderDatabase.getInstance(context);
        ReminderDAO reminderDAO = reminderDatabase.getReminderDAO();

        long savedReminderId = reminderToBeSaved.getId();

        if (reminderToBeSaved.getId() == DEFAULT_ID) {
            savedReminderId = reminderDAO.save(reminderToBeSaved);
        } else {
            reminderDAO.update(reminderToBeSaved);
        }

        this.setUpSavedReminderAlarm(savedReminderId, reminderDAO);

    }

    private void setUpSavedReminderAlarm(long savedReminderId, ReminderDAO reminderDAO) {
        Reminder reminder = reminderDAO.getReminderById(savedReminderId);
        new ReminderAlarmManager(context, Collections.singletonList(reminder)).setUpAllRemindersAlarms();
    }


}
