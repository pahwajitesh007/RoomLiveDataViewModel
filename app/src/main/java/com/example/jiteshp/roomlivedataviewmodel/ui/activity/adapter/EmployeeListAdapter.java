package com.example.jiteshp.roomlivedataviewmodel.ui.activity.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jiteshp.roomlivedataviewmodel.R;
import com.example.jiteshp.roomlivedataviewmodel.db.entity.Employee;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.RecyclerViewHolder> {
    private List<Employee> employeeList;
    Context context;
    private View.OnLongClickListener longClickListener;

    public EmployeeListAdapter(List<Employee> employeeList, Context context) {
        this.context = context;
        this.employeeList = employeeList;
        longClickListener=(View.OnLongClickListener)context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        try {
            holder.mEmpName.setText(employeeList.get(position).getEmployeeName());
            holder.mEmpAddress.setText(employeeList.get(position).getEmployeeAddress());
            holder.mEmpDOB.setText(employeeList.get(position).getEmployeeDateOfBirth());
            holder.itemView.setTag(employeeList.get(position));
            holder.itemView.setOnLongClickListener(longClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void addEmployeeItem(List<Employee> employees) {
        if (employeeList != null && employeeList.size() > 0) {
            employeeList.clear();
        }
        employeeList = employees;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView mEmpName;
        @BindView(R.id.txt_address)
        TextView mEmpAddress;
        @BindView(R.id.txt_date)
        TextView mEmpDOB;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


