package com.anas.realmcrud;

import android.widget.EditText;

public class VariableHolder {

    private static int Task_editId;
    private static EditText eTask_name;

    public static int getTask_editId() {
        return Task_editId;
    }

    public static void setTask_editId(int task_editId) {
        Task_editId = task_editId;
    }

    public static EditText geteTask_name() {
        return eTask_name;
    }

    public static void seteTask_name(EditText eTask_name) {
        VariableHolder.eTask_name = eTask_name;
    }
}
