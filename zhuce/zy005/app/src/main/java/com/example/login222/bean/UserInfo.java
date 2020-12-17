package com.example.login222.bean;

public class UserInfo {
    public long rowid;
    public int sn;
    public String name;
    public int age;
    public boolean states;
    public float height;
    public float weight;
    public String update_time;
    public String phone;
    public String pwd;

    public UserInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        age = 0;
        height=0.0f;
        weight=0.0f;
        states = false;
        update_time = "";
        phone = "";
        pwd = "";
    }
}