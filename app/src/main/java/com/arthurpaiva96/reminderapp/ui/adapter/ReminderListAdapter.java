package com.arthurpaiva96.reminderapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderListAdapter extends BaseAdapter {

    private final List<Reminder> reminders = new ArrayList<>();
    private final Context context;

    public ReminderListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return reminders.size();
    }

    @Override
    public Reminder getItem(int position) {
        return reminders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return reminders.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parentViewGroup) {

        View createdReminderListView = LayoutInflater.from(context).
                inflate(R.layout.activity_reminder_list_item_reminder, parentViewGroup, false);

        setReminderViewText(position, createdReminderListView);

        return createdReminderListView;

    }

    private void setReminderViewText(int position, View createdReminderListView) {
        Reminder reminder = this.reminders.get(position);
        TextView title = createdReminderListView.findViewById(R.id.activity_reminder_list_item_reminder_title);
        TextView description = createdReminderListView.findViewById(R.id.activity_reminder_list_item_reminder_description);
        TextView date = createdReminderListView.findViewById(R.id.activity_reminder_list_item_reminder_date);
        TextView hour = createdReminderListView.findViewById(R.id.activity_reminder_list_item_reminder_hour);

        title.setText(reminder.getTitle());
        description.setText(reminder.getDescription());
        date.setText(reminder.getDate());
        hour.setText(reminder.getHour());
    }

    private void clear() {
        this.reminders.clear();
    }

    private void addAll(List<Reminder> remindersList){
        this.reminders.addAll(remindersList);
    }

    public void remove(Reminder reminder){
        this.reminders.remove(reminder);
        notifyDataSetChanged();
    }

    public void refreshReminderList(List<Reminder> allReminders) {
        this.clear();
        this.addAll(allReminders);
        notifyDataSetChanged();
    }
}
