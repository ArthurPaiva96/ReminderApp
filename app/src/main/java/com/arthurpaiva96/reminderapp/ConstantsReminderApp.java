package com.arthurpaiva96.reminderapp;

public interface ConstantsReminderApp {

    String TITLE_LIST = "Lista de Lembretes";
    String TITLE_ADD_NEW_REMINDER = "Adicionando Novo Lembrete";
    String TITLE_EDIT_REMINDER = "Editando Lembrente";
    String TITLE_EMAIL_FORM = "Configurando e-mail";

    String TITLE_DELETE_REMINDER = "Removendo lembrete";
    String MESSAGE_DELETE_REMINDER = "Deseja remover esse lembrete: ";
    String OPTION_MESSAGE_DELETE_REMINDER_NO = "Não";
    String OPTION_MESSAGE_DELETE_REMINDER_YES = "Sim";

    String TOAST_AFTER_ADD_REMINDER = "Lembrete salvo! ;)";
    String TOAST_REMINDER_FORM_FIELD_CANT_BE_NULL = "Todos os campos precisam ser preenchidos";

    String TOAST_AFTER_ADD_EMAIL = "Novo e-mail definido! ;)";
    String TOAST_REMINDER_FORM_FIELD_NULL_OR_INCORRECT = "Algum campo está vazio ou o e-mail não é Gmail";

    String FOREGROUND_TITLE = "ReminderApp está ativo!";

    String KEY_REMINDER_EXTRA = "reminder";
    int DEFAULT_ID = 0;
    String NOTIFICATION_CHANNEL = "notification_channel";
    String FOREGROUND_CHANNEL = "foreground_channel";

}
