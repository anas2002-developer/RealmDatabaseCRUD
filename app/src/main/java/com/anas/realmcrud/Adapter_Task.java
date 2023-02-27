package com.anas.realmcrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class Adapter_Task extends RealmRecyclerViewAdapter<Model_Task,Adapter_Task.Task_ViewHolder> {


    OrderedRealmCollection<Model_Task> data;
    public Adapter_Task(OrderedRealmCollection<Model_Task> data) {
        super(data, true);
        this.data=data;
    }

    @NonNull
    @Override
    public Task_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row,parent,false);
        return new Task_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Task_ViewHolder holder, int position) {

        final Model_Task model = getItem(position);
        holder.txtTask_id.setText(String.valueOf(model.getTask_id()));
        holder.txtTask_name.setText(model.getTask_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VariableHolder.geteTask_name().setText(data.get(position).getTask_name());
                VariableHolder.setTask_editId(data.get(position).getTask_id());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RealmHelper realmHelper = new RealmHelper();
                realmHelper.Delete(data.get(position).getTask_id());
                return true;
            }
        });
    }

    @Override
    public long getItemId(int index) {
        return getItem(index).getTask_id();

    }

    public class Task_ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTask_id;
        TextView txtTask_name;
        public Task_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTask_id = itemView.findViewById(R.id.txtTask_id);
            txtTask_name = itemView.findViewById(R.id.txtTask_name);
        }
    }
}
