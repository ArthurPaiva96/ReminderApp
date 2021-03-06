package com.arthurpaiva96.reminderapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.ui.ReminderListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.TITLE_LIST;

public class ReminderListActivity extends AppCompatActivity {


    private ReminderListView reminderListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);
        setTitle(TITLE_LIST);
        this.reminderListView  = new ReminderListView(ReminderListActivity.this);

        final ListView reminderListView = findViewById(R.id.activity_reminder_list_list);

        //Plus Button
        FloatingActionButton floatAddRemindersButton = findViewById(R.id.activity_menu_float_add);
        floatAddRemindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReminderListActivity.this,
                        ReminderFormActivity.class));
            }
        });


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
