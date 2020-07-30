package com.arthurpaiva96.reminderapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.broadcast.ReminderAlarmManager;
import com.arthurpaiva96.reminderapp.database.ReminderDatabase;
import com.arthurpaiva96.reminderapp.database.dao.ReminderDAO;
import com.arthurpaiva96.reminderapp.model.Reminder;
import com.arthurpaiva96.reminderapp.ui.activity.ReminderFormActivity;
import com.arthurpaiva96.reminderapp.ui.adapter.ReminderListAdapter;

import java.util.Arrays;

import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.KEY_REMINDER_EXTRA;
import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.MESSAGE_DELETE_REMINDER;
import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.OPTION_MESSAGE_DELETE_REMINDER_NO;
import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.OPTION_MESSAGE_DELETE_REMINDER_YES;
import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.TITLE_DELETE_REMINDER;

public class ReminderListView {

    private Context context;
    private ReminderListAdapter adapter;
    private ReminderDAO reminderDAO;

    public ReminderListView(Context context){
        this.context = context;

        ReminderDatabase reminderDatabase = ReminderDatabase.getInstance(context);
        this.reminderDAO = reminderDatabase.getRoomReminderDAO();
    }

    public void reminderListItemClickBehavior(ListView reminderListView) {

        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Reminder clickedReminder = (Reminder) adapterView.getItemAtPosition(position);

                editReminder(clickedReminder);
            }
        });
    }

    public void reminderListItemLongClickMenuBehavior(MenuItem item){
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
    }

    private void editReminder(Reminder clickedReminder) {
        this.context.startActivity(new Intent(this.context,
                ReminderFormActivity.class).putExtra(KEY_REMINDER_EXTRA, clickedReminder));

        ((Activity) this.context).finish();
    }

    private void deleteReminder(final Reminder chosenReminder) {

        new AlertDialog
                .Builder(this.context)
                .setTitle(TITLE_DELETE_REMINDER)
                .setMessage(MESSAGE_DELETE_REMINDER + chosenReminder.getTitle() + "?")
                .setNegativeButton(OPTION_MESSAGE_DELETE_REMINDER_NO, null)
                //DELETE THE REMINDER
                .setPositiveButton(OPTION_MESSAGE_DELETE_REMINDER_YES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        new ReminderAlarmManager(context, Arrays.asList(chosenReminder)).deleteAllReminders();
                        reminderDAO.delete(chosenReminder);
                        adapter.remove(chosenReminder);
                    }
                })
                .show();

    }

    public void refreshReminderList(){

        adapter.refreshReminderList(reminderDAO.getAllReminders());
    }

    public void configureAdapter(ListView listaDeAlunos) {
        adapter = new ReminderListAdapter(context);
        listaDeAlunos.setAdapter(adapter);
    }



}
