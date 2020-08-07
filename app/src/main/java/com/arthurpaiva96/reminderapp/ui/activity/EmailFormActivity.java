package com.arthurpaiva96.reminderapp.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.database.EmailDatabase;
import com.arthurpaiva96.reminderapp.model.EmailReminder;
import com.arthurpaiva96.reminderapp.ui.EmailFormView;

import java.util.List;

import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.TITLE_EMAIL_FORM;

public class EmailFormActivity extends AppCompatActivity {


    private EditText email;
    private EditText password;

    private final EmailFormView emailFormView = new EmailFormView(EmailFormActivity.this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_form);
        setTitle(TITLE_EMAIL_FORM);

        getUserInputInfo();

        configureSaveButton();

    }

    @Override
    protected void onResume() {
        super.onResume();
        retriveEmail();
    }

    private void getUserInputInfo() {
        this.email = findViewById(R.id.activity_email_form_email_input);
        this.password = findViewById(R.id.activity_email_form_password_input);
    }

    private void configureSaveButton() {
        Button saveEmailButton = findViewById(R.id.activity_email_form_save_button);

        this.emailFormView.saveButtonBehavior(saveEmailButton,
                this.email, this.password);
    }

    private void retriveEmail() {
        EmailDatabase emailDatabase = EmailDatabase.getInstance(this);
        List<EmailReminder> allEmails = emailDatabase.getEmailDAO().getAllEmails();

        if(allEmails.size() > 0){
            String email = allEmails.get(0).getEmail();
            String password = allEmails.get(0).getPassword();

            this.email.setText(email);
            this.password.setText(password);
        }
    }
}
