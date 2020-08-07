package com.arthurpaiva96.reminderapp.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arthurpaiva96.reminderapp.database.EmailDatabase;
import com.arthurpaiva96.reminderapp.database.dao.EmailDAO;
import com.arthurpaiva96.reminderapp.model.EmailReminder;

import java.util.List;

import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.TOAST_AFTER_ADD_EMAIL;
import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.TOAST_REMINDER_FORM_FIELD_NULL_OR_INCORRECT;

public class EmailFormView {

    private final Context context;

    public EmailFormView(Context context) {
        this.context = context;
    }

    public void saveButtonBehavior(Button saveEmailButton, final EditText email, final EditText password){

        saveEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean savedSuccessfully = saveUserInputAsEmail(
                        email.getText().toString().trim(),
                        password.getText().toString().trim());

                if(savedSuccessfully){

                    Toast.makeText(context, TOAST_AFTER_ADD_EMAIL,
                            Toast.LENGTH_LONG).show();

                    ((Activity) context).finish();

                }else{
                    Toast.makeText(context, TOAST_REMINDER_FORM_FIELD_NULL_OR_INCORRECT,
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private boolean saveUserInputAsEmail(String email, String password) {
        EmailReminder newEmailReminder = new EmailReminder(email, password);

        if(newEmailReminder.allFieldsAreNotNull() && newEmailReminder.isGmail()){
            saveEmail(newEmailReminder);
            return true;
        }

        return false;
    }

    private void saveEmail(EmailReminder newEmailReminder) {
        EmailDatabase database = EmailDatabase.getInstance(context);
        EmailDAO emailDAO = database.getEmailDAO();

        List<EmailReminder> allEmailReminders = emailDAO.getAllEmails();

        for(EmailReminder oldEmailReminder : allEmailReminders){
            emailDAO.delete(oldEmailReminder);
        }

        emailDAO.save(newEmailReminder);
    }
}
