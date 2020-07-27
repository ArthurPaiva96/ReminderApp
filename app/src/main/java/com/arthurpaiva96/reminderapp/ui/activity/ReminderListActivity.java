package com.arthurpaiva96.reminderapp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.dao.ReminderDAO;
import com.arthurpaiva96.reminderapp.model.Reminder;
import com.arthurpaiva96.reminderapp.ui.ReminderListView;

import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.TITLE_LIST;

public class ReminderListActivity extends AppCompatActivity {


    private ReminderListView reminderListView = new ReminderListView(ReminderListActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);
        setTitle(TITLE_LIST);

        final ListView reminderListView = findViewById(R.id.activity_reminder_list_list);


        this.reminderListView.configureAdapter(reminderListView);
        this.reminderListView.reminderListItemClickBehavior(reminderListView);

        registerForContextMenu(reminderListView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.activity_reminder_list_menu, menu);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        this.reminderListView.reminderListItemLongClickMenuBehavior(item);

        return super.onContextItemSelected(item);
    }



    @Override
    protected void onResume() {
        super.onResume();

        this.reminderListView.refreshReminderList();
    }

}
