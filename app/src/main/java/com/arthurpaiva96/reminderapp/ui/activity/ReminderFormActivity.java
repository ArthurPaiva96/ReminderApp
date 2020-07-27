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
import com.arthurpaiva96.reminderapp.ui.ReminderFormView;

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
    private ReminderFormView reminderFormView = new ReminderFormView(ReminderFormActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_form);
        setTitle(TITLE_ADD_NEW_REMINDER);

        getUserInputInfo();
        fillFormWithExtra();

        this.reminderFormView.configureDatePicker(this.reminderDateInput);
        this.reminderFormView.configureTimePicker(this.reminderHourInput);

        configureSaveButton();


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

    private void configureSaveButton() {

        Button saveReminderButton = findViewById(R.id.activity_reminder_form_save_button);

        this.reminderFormView.saveButtonBehavior(saveReminderButton, this.reminder,
                this.reminderTitleInput,
                this.reminderDescriptionInput,
                this.reminderDateInput,
                this.reminderHourInput);

    }




}