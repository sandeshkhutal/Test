package com.sandesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllStudentsActivity extends AppCompatActivity {
    List<StudentModel> employeeList;
    DatabaseManager mDatabase;
    ListView listView;
    StudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_students);



        //Instantiating the database manager object
        mDatabase = new DatabaseManager(this);

        employeeList = new ArrayList<>();
        listView = findViewById(R.id.listView);

        loadEmployeesFromDatabase();
    }

    private void loadEmployeesFromDatabase() {
        //we are here using the DatabaseManager instance to get all employees
        Cursor cursor = mDatabase.getAllEmployees();

        if (cursor.moveToFirst()) {
            do {
                employeeList.add(new StudentModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)

                ));
            } while (cursor.moveToNext());

            //passing the databasemanager instance this time to the adapter
            StudentAdapter adapter = new StudentAdapter(this, R.layout.item_students_list, employeeList, mDatabase);
            listView.setAdapter(adapter);
        }
    }
}