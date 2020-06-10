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


public class ReminderFormActivity extends AppCompatActivity {


    private EditText reminderTitleInput;
    private EditText reminderDescriptionInput;
    private EditText reminderDateInput;
    private EditText reminderHourInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_form);
        setTitle("Adicinando Novo Lembrete");

        getUserInputInfo();

        Button saveReminderButton = findViewById(R.id.activity_reminder_form_save_button);

        saveReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUserInputAsReminder();

                Toast.makeText(ReminderFormActivity.this, "Lembrete adicionado! ;)",
                        Toast.LENGTH_LONG).show();

                startActivity(new Intent(ReminderFormActivity.this, ReminderListActivity.class));

                finish();

            }
        });

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

        new ReminderDAO().save(new Reminder(reminderTitle, reminderDescription, reminderDate, reminderHour));
    }
}