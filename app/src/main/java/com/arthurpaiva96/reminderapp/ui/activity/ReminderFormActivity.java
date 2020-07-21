package com.arthurpaiva96.reminderapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.dao.ReminderDAO;
import com.arthurpaiva96.reminderapp.model.Reminder;

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