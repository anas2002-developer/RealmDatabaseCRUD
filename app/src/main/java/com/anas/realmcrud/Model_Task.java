package com.anas.realmcrud;

import io.realm.RealmObject;

public class Model_Task extends RealmObject {

    int Task_id;
    String Task_name;

    public Model_Task() {
    }

    public Model_Task( String task_name) {
        Task_name = task_name;
    }

    public int getTask_id() {
        return Task_id;
    }

    public void setTask_id(int task_id) {
        Task_id = task_id;
    }

    public String getTask_name() {
        return Task_name;
    }

    public void setTask_name(String task_name) {
        Task_name = task_name;
    }
}
