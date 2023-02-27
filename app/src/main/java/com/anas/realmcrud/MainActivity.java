package com.anas.realmcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView vRV;
    EditText eTask_name;
    Button btnInsert,btnUpdate;

    Realm realm;
    RealmHelper realmHelper;

    Adapter_Task adapter_task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(getApplicationContext());
        realmHelper = new RealmHelper();
//                RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name(Realm.DEFAULT_REALM_NAME)
//                .schemaVersion(0)
//                .allowWritesOnUiThread(true)
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        realm = Realm.getInstance(realmConfiguration);

        eTask_name = findViewById(R.id.eTask_name);
        VariableHolder.seteTask_name(eTask_name);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        vRV = findViewById(R.id.vRV);
        vRV.setLayoutManager(new LinearLayoutManager(this));

        UpdateRecycler();






        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnInsert:
                Insert();
                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnUpdate:
                Update();
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void Update() {

        String Task_name = eTask_name.getText().toString();
        realmHelper.Edit(VariableHolder.getTask_editId(),Task_name);
        eTask_name.setText("");
        UpdateRecycler();
    }


    private void Insert() {

        String Task_name = eTask_name.getText().toString();
        realmHelper.Insert(new Model_Task(Task_name));
        eTask_name.setText("");
        UpdateRecycler();
    }


    public void UpdateRecycler() {

        adapter_task = new Adapter_Task((OrderedRealmCollection<Model_Task>) realmHelper.getData());
        vRV.setAdapter(adapter_task);
    }
}