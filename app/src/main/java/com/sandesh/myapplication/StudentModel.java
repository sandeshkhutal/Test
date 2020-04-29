package com.sandesh.myapplication;

public class StudentModel {
    private String rollNo;
    private String Name;
    private int id;
    private String Score;

    StudentModel(int id, String rollNo, String name, String score) {
        this.id = id;
        this.rollNo = rollNo;
        this.Name = name;
        this.Score = score;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }
}
