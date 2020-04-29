package com.sandesh.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<StudentModel> {

    private Context mCtx;
    private int listLayoutRes;
    private List<StudentModel> employeeList;
    private DatabaseManager mDatabase;


    StudentAdapter(Context mCtx, int listLayoutRes, List<StudentModel> employeeList, DatabaseManager mDatabase) {
        super(mCtx, listLayoutRes, employeeList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.employeeList = employeeList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        @SuppressLint("ViewHolder") View view = inflater.inflate(listLayoutRes, null);

        //getting employee of the specified position
        StudentModel employee = employeeList.get(position);


        //getting views
        AppCompatTextView textViewName = view.findViewById(R.id.txt_Name);
        AppCompatTextView textViewRoll = view.findViewById(R.id.txt_Roll);
        AppCompatTextView textViewScore = view.findViewById(R.id.txt_Score);


        //adding data to views
        textViewName.setText(employee.getName());
        textViewRoll.setText(employee.getRollNo());
        textViewScore.setText(employee.getScore());

        return view;
    }
}
