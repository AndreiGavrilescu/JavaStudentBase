package com.core;



/**
 * Created by Andrei on 14-Mar-16.
 */
public class Student {
    private int id;
    private String name;
    private String birthday;
    private String address;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student(int id, String name, String birthday, String address){
        super();
        this.id = id;
        this.name = name;
        this.birthday=birthday;
        this.address=address;

    }
}
