package com.arthurpaiva96.reminderapp.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.dao.ReminderDAO;
import com.arthurpaiva96.reminderapp.model.Reminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.KEY_REMINDER_EXTRA;
import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.TITLE_ADD_NEW_REMINDER;
import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.TITLE_EDIT_REMINDER;
import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.TOAST_AFTER_ADD_REMINDER;


public class ReminderFormActivity extends AppCompatActivity {


    private EditText reminderTitleInput;
    private EditText reminderDescriptionInput;
    private EditText reminderDateInput;
    private EditText reminderHourInput;

    private Reminder reminder = new Reminder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_form);
        setTitle(TITLE_ADD_NEW_REMINDER);

        getUserInputInfo();


        configureDatePicker();

        configureTimePicker();

        configureSaveButton();


    }

    private void configureTimePicker() {
        final TimePickerDialog.OnTimeSetListener actionsAfterChooseTime = setHourAfterChoose();

        this.reminderHourInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ReminderFormActivity.this,
                        actionsAfterChooseTime, 0, 0, true)
                        .show();
            }
        });
    }

    private TimePickerDialog.OnTimeSetListener setHourAfterChoose() {
        return new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                reminderHourInput.setText(String.format(Locale.getDefault(),"%02d:%02d",hourOfDay,minute));
            }
        };
    }


    private void configureDatePicker() {

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener actionsAfterChooseDate = setDateAfterChoose(calendar);

        this.reminderDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ReminderFormActivity.this,
                        actionsAfterChooseDate,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener setDateAfterChoose(final Calendar calendar) {
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

    private void configureSaveButton() {
        Button saveReminderButton = findViewById(R.id.activity_reminder_form_save_button);

        fillFormWithExtra();

        saveReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUserInputAsReminder();

                Toast.makeText(ReminderFormActivity.this, TOAST_AFTER_ADD_REMINDER,
                        Toast.LENGTH_LONG).show();

                startActivity(new Intent(ReminderFormActivity.this, ReminderListActivity.class));

                finish();

            }
        });
    }

    private void fillFormWithExtra() {
        Reminder reminderExtra = (Reminder) getIntent().getSerializableExtra(KEY_REMINDER_EXTRA);

        if(reminderExtra != null){

            setTitle(TITLE_EDIT_REMINDER);

            this.reminderTitleInput.setText(reminderExtra.getTitle());
            this.reminderDescriptionInput.setText(reminderExtra.getDescription());
            this.reminderDateInput.setText(reminderExtra.getDate());
            this.reminderHourInput.setText(reminderExtra.getHour());
            this.reminder = reminderExtra;

        }
    }

    private void getUserInputInfo() {

        this.reminderTitleInput = findViewById(R.id.activity_reminder_form_title);
        this.reminderDescriptionInput = findViewById(R.id.activity_reminder_form_description);
        this.reminderDateInput = findViewById(R.id.activity_reminder_form_date);
        this.reminderHourInput = findViewById(R.id.activity_reminder_form_hour);

        this.reminderDateInput.setInputType(EditorInfo.TYPE_NULL);
        this.reminderHourInput.setInputType(EditorInfo.TYPE_NULL);
    }

    private void saveUserInputAsReminder() {

        String reminderTitle = this.reminderTitleInput.getText().toString();
        String reminderDescription = this.reminderDescriptionInput.getText().toString();
        String reminderDate = this.reminderDateInput.getText().toString();
        String reminderHour = this.reminderHourInput.getText().toString();

        this.reminder.setTitle(reminderTitle);
        this.reminder.setDescription(reminderDescription);
        this.reminder.setDate(reminderDate);
        this.reminder.setHour(reminderHour);

        new ReminderDAO().editReminder(this.reminder);

    }


}