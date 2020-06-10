package com.arthurpaiva96.reminderapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Lista de Lembretes Button
        Button showRemindersButton = findViewById(R.id.activity_menu_reminder_list);
        showRemindersButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainMenuActivity.this,
                         ReminderListActivity.class));
             }
         });

        //Novo Lembrete Button
        Button addRemindersButton = findViewById(R.id.activity_menu_new_reminder);
        addRemindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMenuActivity.this.startAddReminderActivity();
            }
        });

        //Plus Button
        FloatingActionButton floatAddRemindersButton = findViewById(R.id.activity_menu_float_add);
        floatAddRemindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMenuActivity.this.startAddReminderActivity();
            }
        });
    }

    private void startAddReminderActivity(){
        startActivity(new Intent(MainMenuActivity.this,
                ReminderFormActivity.class));
    }
}
