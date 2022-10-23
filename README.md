# ReminderApp
ReminderApp is an app that uses notifications and alarms to remind the user about something. It can also be configured to send an e-mail to him/her.

# How to use

## Adding a new reminder

1. Open the app.
2. Tap the menu item "NOVO LEMBRETE".
3. Tap the text field below "Título:" and write the reminder title.
4. Tap the text field below "Descrição:" and write the reminder description.
5. Tap the text field below "Data:" and a date picker will pop up. Choose the date on which the reminder will happen.
6. Tap the text field below "Hora:" and a time picker will pop up. Choose the hour at which the reminder will happen.
7. Tap the button "ME LEMBRE!" to save the reminder. You will be redirected to the reminders list.

Alternatively:

1. Open the app.
2. Tap the menu item "LISTA DE LEMBRETES".
3. Tap the plus("+") button at the bottom right corner.
4. Proceed from the above guide's step 3.

## Editing a reminder

1. Open the app.
2. Tap the menu item "LISTA DE LEMBRETES".
3. Tap one of the reminders on the list. You will be redirected to the edit screen of this reminder
4. Tap the text field of the information(s) you wish to edit and change.
5. After all the changes are done tap the button "ME LEMBRE!".

Alternatively:

1. Open the app.
2. Tap the menu item "LISTA DE LEMBRETES".
3. Tap and hold one of the reminders on the list. A context menu will pop up.
4. Tap the option "Editar lembrete".
5. Proceed from the above guide's step 4.

## Deleting a reminder

1. Open the app.
2. Tap the menu item "LISTA DE LEMBRETES".
3. Tap and hold one of the reminders on the list. A context menu will pop up.
4. Tap the option "Remover lembrete".
5. An alert dialog screen message will pop up. Tap "SIM" to delete the reminder or "NÃO"(or tap outside the dialog screen) to cancel the action.

## Configuring an e-mail to send and receive reminders.

To make sure the app is safe you can see the [e-mail form code](app/src/main/java/com/arthurpaiva96/reminderapp/ui/EmailFormView.java) and the [e-mail sender code](app/src/main/java/com/arthurpaiva96/reminderapp/broadcast/EmailSender.java).

You have to remember to enable the SMTP Settings for a Gmail account before any reminder e-mail is sent.

1. Open the app.
2. Tap the menu item "CONFIGURAR E-MAIL".
3. Tap the text field below "E-mail:" and write your e-mail. The e-mail must be a Gmail one.
4. Tap the text field below "Senha:" and write this e-mail password. You can click on the eye icon to display the password or hide it.
5. Tap the "SALVAR E-MAIL" button.

# Warnings

- This app was made with Brazilian users in mind, and because of that, its interface is in Brazilian Portuguese.
- The phone must be turned on for the e-mail to be sent.
- The phone can't be on silent for the alarm to ring.
- This was my first time making an app, so I used a ListView instead of a RecyclerView.
