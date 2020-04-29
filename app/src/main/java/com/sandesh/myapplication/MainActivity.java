package com.sandesh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseManager mDatabase;
    private AppCompatEditText edt_RollNo, edt_Name, edt_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = new DatabaseManager(getApplicationContext());
        edt_RollNo = findViewById(R.id.edt_RollNo);
        edt_Name = findViewById(R.id.edt_Name);
        edt_score = findViewById(R.id.edt_score);


        AppCompatButton btn_Add = findViewById(R.id.btn_Add);
        AppCompatButton btn_Update = findViewById(R.id.btn_Update);
        AppCompatButton btn_Delete = findViewById(R.id.btn_Delete);
        AppCompatButton btn_View = findViewById(R.id.btn_View);


        btn_Add.setOnClickListener(this);
        btn_Update.setOnClickListener(this);
        btn_Delete.setOnClickListener(this);
        btn_View.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Add:
                String rollNo = Objects.requireNonNull(edt_RollNo.getText()).toString();
                String student_Name = Objects.requireNonNull(edt_Name.getText()).toString();
                String score = Objects.requireNonNull(edt_score.getText()).toString();
                if (rollNo.isEmpty()) {
                    edt_RollNo.setError("Roll No can't be empty");
                    edt_RollNo.requestFocus();
                    return;
                }
                if (student_Name.isEmpty()) {
                    edt_Name.setError("Name can't be empty");
                    edt_Name.requestFocus();
                    return;
                }
                if (score.isEmpty()) {
                    edt_score.setError("Name can't be empty");
                    edt_score.requestFocus();
                    return;
                }

                //adding the employee with the DatabaseManager instance

                if (mDatabase.dididExist(Integer.parseInt(rollNo))) {
                    Toast.makeText(this, "Already Exist", Toast.LENGTH_SHORT).show();
                } else {
                    if (mDatabase.addEmployee(rollNo, student_Name, score)) {
                        Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();
                        edt_RollNo.setText("");
                        edt_Name.setText("");
                        edt_score.setText("");
                    } else {
                        Toast.makeText(this, "Could not add employee", Toast.LENGTH_SHORT).show();
                    }
                }
                break;


            case R.id.btn_Update:

                break;

            case R.id.btn_Delete:

                break;

            case R.id.btn_View:
                //Exp

                startActivity(new Intent(getApplicationContext(), ViewAllStudentsActivity.class));
                break;
        }
    }
}
