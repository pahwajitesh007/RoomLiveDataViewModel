package com.example.jiteshp.roomlivedataviewmodel.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.example.jiteshp.roomlivedataviewmodel.db.entity.Employee;
import com.example.jiteshp.roomlivedataviewmodel.utils.DateConverter;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface EmployeeDao {

    @Query("select * from Employee")
    LiveData<List<Employee>> getAllEmpoyee();

    @Query("select * from Employee where id=:id")
    Employee getEmpoyeeById(int id);

    @Insert(onConflict = REPLACE)
    void insertEmplpoyee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);


}
