package com.arthurpaiva96.reminderapp.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.dao.ReminderDAO;

public class ReminderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);
        setTitle("Lista de Lembretes");

    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView reminderListView = findViewById(R.id.activity_reminder_list_list);
        reminderListView.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, new ReminderDAO().getAllReminders()));
    }
}
