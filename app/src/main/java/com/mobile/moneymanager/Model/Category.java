package com.mobile.moneymanager.Model;

import java.io.Serializable;

public class Category implements Serializable{

    public String name = "";
    //public String image = "";
    public  int id;
    public int income;

    public Category(int id,String name, int income) {
        this.name = name;
        this.income = income;
        this.id=id;
    }
    public Category(){};

    public int getId() {
        return id;
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

    public int isIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}
