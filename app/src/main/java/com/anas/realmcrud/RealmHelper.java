package com.anas.realmcrud;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmHelper {

    RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name(Realm.DEFAULT_REALM_NAME)
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build();
    Realm realm = Realm.getInstance(realmConfiguration);

    public void Insert(final Model_Task task){
        Number number = realm.where(Model_Task.class).max("Task_id");
        final int nextId;

        if (number==null){
            nextId=0;
        }
        else {
            nextId= number.intValue() + 1;
        }

        task.setTask_id(nextId);

        realm.beginTransaction();
        realm.copyToRealm(task);
        realm.commitTransaction();
    }

    public List<Model_Task> getData(){
        return realm.where(Model_Task.class).findAll();
    }

    public void Edit(final int Task_id, final String Task_name){
        final Model_Task model = realm.where(Model_Task.class).equalTo("Task_id",Task_id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.setTask_name(Task_name);
                realm.copyFromRealm(model);
            }
        });
    }

    public void Delete(final int Task_id){
        final Model_Task model = realm.where(Model_Task.class).equalTo("Task_id",Task_id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm();
            }
        });
    }
}
