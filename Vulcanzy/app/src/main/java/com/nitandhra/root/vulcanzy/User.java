package com.nitandhra.root.vulcanzy;

public class User {
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    public String getClg_name() {
        return clg_name;
    }

    public String getGender() {
        return gender;
    }

    public Long getPaid() {
        return paid;
    }

    String username,name,email,phone_number,password,clg_name,gender;
    Long paid;
    public User() {
    }
    public User(String uname,String fname, String eml,String mob,String pass,String coll,String gend) {
        this.username = uname;
        this.email = eml;
        this.name=fname;
        this.phone_number=mob;
        this.password=pass;
        this.clg_name=coll;
        this.gender=gend;
        this.paid=Long.valueOf(0);
    }
    public String getUsername()
    {
        return this.username;
    }
}
