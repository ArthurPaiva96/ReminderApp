package com.arthurpaiva96.reminderapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.dao.ReminderDAO;
import com.arthurpaiva96.reminderapp.model.Reminder;

import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.KEY_REMINDER_EXTRA;
import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.TITLE_LIST;

public class ReminderListActivity extends AppCompatActivity {

    private ArrayAdapter<Reminder> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);
        setTitle(TITLE_LIST);

        final ListView reminderListView = findViewById(R.id.activity_reminder_list_list);
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        reminderListView.setAdapter(adapter);

        registerForContextMenu(reminderListView);

        configureItemClickListener(reminderListView);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.activity_reminder_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int chosenOptionId = item.getItemId();
        Reminder chosenReminder = adapter.getItem(menuInfo.position);


        switch (chosenOptionId){
            case R.id.activity_reminder_list_menu_edit:
                editReminder(chosenReminder);
                break;
            case R.id.activity_reminder_list_menu_delete:
                deleteReminder(chosenReminder);
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void deleteReminder(Reminder chosenReminder) {

        new ReminderDAO().deleteAReminder(chosenReminder);
        adapter.remove(chosenReminder);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.clear();
        adapter.addAll(new ReminderDAO().getAllReminders());

    }


    private void configureItemClickListener(ListView reminderListView) {

        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Reminder clickedReminder = (Reminder) adapterView.getItemAtPosition(position);

                editReminder(clickedReminder);
            }
        });
    }

    private void editReminder(Reminder clickedReminder) {
        startActivity(new Intent(ReminderListActivity.this,
                ReminderFormActivity.class).putExtra(KEY_REMINDER_EXTRA, clickedReminder));

        finish();
    }
}
