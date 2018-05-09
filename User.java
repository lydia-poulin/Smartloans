package com.example.user.smartloans.models;

public class User {

    public String username;
    public String email;
    public String mob_no;
    public String password_hash;
    public String id;
    public String user_role;

    public User(String username,String email,String mob_no,String password_hash){
        this.username = username;
        this.email = email;
        this.mob_no = mob_no;
        this.password_hash = password_hash;
        this.id = id;
        this.user_role = user_role;
    }
    public User(){

    }
}
