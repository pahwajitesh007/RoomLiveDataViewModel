package com.example.jiteshp.roomlivedataviewmodel.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


import com.example.jiteshp.roomlivedataviewmodel.R;
import com.example.jiteshp.roomlivedataviewmodel.db.entity.Employee;
import com.example.jiteshp.roomlivedataviewmodel.ui.activity.adapter.EmployeeListAdapter;
import com.example.jiteshp.roomlivedataviewmodel.viewmodel.EmployeeListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmployeeListActivity extends AppCompatActivity implements View.OnLongClickListener {
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    @BindView(R.id.btn_add)
    Button mBtnAddEmp;
    private EmployeeListAdapter mEmployeeListAdapter;
    private EmployeeListViewModel employeeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mEmployeeListAdapter = new EmployeeListAdapter(new ArrayList<Employee>(), this);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setAdapter(mEmployeeListAdapter);
        employeeListViewModel = ViewModelProviders.of(this).get(EmployeeListViewModel.class);

        employeeListViewModel.getAllEmployeeList().observe(EmployeeListActivity.this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {

                mEmployeeListAdapter.addEmployeeItem(employees);

            }
        });

    }

    @OnClick(R.id.btn_add)
    public void addEmployee() {
        Intent mIntent = new Intent(EmployeeListActivity.this, AddEmployeeActivity.class);
        startActivity(mIntent);

    }

    @Override
    public boolean onLongClick(View view) {
        Employee employee = (Employee) view.getTag();
        employeeListViewModel.deleteEmployee(employee);
        return true;
    }
}
