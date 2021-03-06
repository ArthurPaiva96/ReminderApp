package com.arthurpaiva96.reminderapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        //Novo Lembrete Button
        Button addRemindersButton = findViewById(R.id.activity_menu_new_reminder);
        addRemindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,
                        ReminderFormActivity.class));
            }
        });

        //Lista de Lembretes Button
        Button showRemindersButton = findViewById(R.id.activity_menu_reminder_list);
        showRemindersButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainMenuActivity.this,
                         ReminderListActivity.class));
             }
         });


        //Configure Email Button
        Button configureEmailButton = findViewById(R.id.activity_menu_configure_email);
        configureEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this,
                        EmailFormActivity.class));
            }
        });


    }


}
