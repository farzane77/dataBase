package com.example.espinas.emdb.Model;

public class Person {

    private int ID;
    private String name;
    private int age;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString(){
        return this.getName()+" "+this.getAge();
    }
}
