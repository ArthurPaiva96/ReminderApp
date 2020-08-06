package com.arthurpaiva96.reminderapp.ui.activity;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.model.Reminder;
import com.arthurpaiva96.reminderapp.ui.ReminderFormView;

import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.KEY_REMINDER_EXTRA;
import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.TITLE_ADD_NEW_REMINDER;
import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.TITLE_EDIT_REMINDER;


public class ReminderFormActivity extends AppCompatActivity {


    private EditText reminderTitleInput;
    private EditText reminderDescriptionInput;
    private EditText reminderDateInput;
    private EditText reminderHourInput;

    private Reminder reminder = new Reminder();
    private final ReminderFormView reminderFormView = new ReminderFormView(ReminderFormActivity.this);

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

        this.reminderTitleInput = findViewById(R.id.activity_reminder_form_title_input);
        this.reminderDescriptionInput = findViewById(R.id.activity_reminder_form_description_input);
        this.reminderDateInput = findViewById(R.id.activity_reminder_form_date_input);
        this.reminderHourInput = findViewById(R.id.activity_reminder_form_hour_input);

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