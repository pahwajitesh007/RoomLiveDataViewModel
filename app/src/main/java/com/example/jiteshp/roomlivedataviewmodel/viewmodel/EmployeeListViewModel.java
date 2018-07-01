package com.example.jiteshp.roomlivedataviewmodel.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.jiteshp.roomlivedataviewmodel.db.AppDataBase;
import com.example.jiteshp.roomlivedataviewmodel.db.entity.Employee;

import java.util.List;

public class EmployeeListViewModel extends AndroidViewModel {
    private LiveData<List<Employee>> mListLiveData;

    private AppDataBase mAppDataBase;

    public EmployeeListViewModel(@NonNull Application application) {
        super(application);

        mAppDataBase = AppDataBase.getINSTANCE(this.getApplication());
        mListLiveData = mAppDataBase.getEmployeeDao().getAllEmpoyee();
    }

    public LiveData<List<Employee>> getAllEmployeeList() {
        return mListLiveData;
    }

    public void deleteEmployee(Employee employee) {
        new deleteAsyncTaskEmployee(mAppDataBase).execute(employee);
    }

    private static class deleteAsyncTaskEmployee extends AsyncTask<Employee, Void, Void> {
        private AppDataBase mAppDataBase;

        public deleteAsyncTaskEmployee(AppDataBase mAppDataBase) {
            this.mAppDataBase = mAppDataBase;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            mAppDataBase.getEmployeeDao().deleteEmployee(employees[0]);
            return null;
        }
    }
}
