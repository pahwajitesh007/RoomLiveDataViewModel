package com.example.jiteshp.roomlivedataviewmodel.ui.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiteshp.roomlivedataviewmodel.R;
import com.example.jiteshp.roomlivedataviewmodel.db.AppDataBase;
import com.example.jiteshp.roomlivedataviewmodel.db.entity.Employee;
import com.example.jiteshp.roomlivedataviewmodel.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEmployeeActivity extends AppCompatActivity {

    @BindView(R.id.edt_name)
    EditText mEmpName;

    @BindView(R.id.edt_address)
    EditText mEmpAddress;

    @BindView(R.id.btn_date)
    Button mBtnDate;

    @BindView(R.id.btn_submit)
    Button mBtnSubmit;

    @BindView(R.id.txt_date)
    TextView mTxtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_date)
    public void getmBtnDate() {

        setDatePicker();
    }

    @OnClick(R.id.btn_submit)
    public void saveEmployeeDetails() {
        try {
            if (!mEmpName.getText().toString().trim().equals("")) {
                if (!mEmpAddress.getText().toString().trim().equals("")) {
                    String name = mEmpName.getText().toString();
                    String address = mEmpAddress.getText().toString();
                    String dob = mTxtDate.getText().toString();// dob is not mandatory
                    Employee employee = new Employee();
                    employee.setEmployeeName(name);
                    employee.setEmployeeAddress(address);
                    employee.setEmployeeDateOfBirth(dob);
                    new InsertAsyncTaskClass(this).execute(employee);
                } else {
                    //address
                    Toast.makeText(this, R.string.enter_address,Toast.LENGTH_SHORT).show();
                }
            } else {
                //name
                Toast.makeText(this, R.string.enter_name,Toast.LENGTH_SHORT).show();
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }

    public void setDatePicker() {
        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateSearch(myCalendar);
            }

        };
        DatePickerDialog dialog = new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        dialog.show();


    }

    public void updateDateSearch(Calendar myCalendar) {
        if (myCalendar != null) {
            try {
                String myFormat = Constant.DATE_FORMAT_FILTER;
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                mTxtDate.setText(sdf.format(myCalendar.getTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class InsertAsyncTaskClass extends AsyncTask<Employee, Void, Void> {

        private Context context;

        public InsertAsyncTaskClass(Context context) {

            this.context = context;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            AppDataBase.getINSTANCE(context).getEmployeeDao().insertEmplpoyee(employees[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent mIntent = new Intent(AddEmployeeActivity.this, EmployeeListActivity.class);
            startActivity(mIntent);
        }
    }
}
