package com.example.barbershop;

import java.util.Calendar;

public class Client {

    private int index;
    private String clientName;
    private String phoneNumber;
    private String dogName;
    private Calendar date;
    private int payment;
    private String dogStatus;

    public Client(){
        this.index = 0;
        this.clientName = "";
        this.phoneNumber = "";
        this.dogName = "";
        this.payment = 0;
        this.dogStatus = "";
        this.date = Calendar.getInstance();
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

    public void setName(String name){
        this.clientName = name;
    }

    public void  setPhone(String phone){
        this.phoneNumber = phone;
    }

    public void setDogName(String dogName){
        this.dogName = dogName;
    }

    public void setDate(int day, int month, int year, int hour, int minute){
        this.date.set(Calendar.DAY_OF_MONTH, day);
        this.date.set(Calendar.MONTH, month);
        this.date.set(Calendar.YEAR, year);
        this.date.set(Calendar.HOUR, hour);
        this.date.set(Calendar.MINUTE, minute);
    }

    public void setPayment(int p){
        this.payment = p;
    }

    public void setDogStatus(String status){
        this.dogStatus = status;
    }

    public String getName(){
        return this.clientName;
    }

    public String getPhoneNumber(){
        return  this.phoneNumber;
    }

    public String getDogName(){
        return this.dogName;
    }

    public int getPayment(){
        return this.payment;
    }

    public Calendar getDate(){
        return this.date;
    }

    public String getDogStatus(){
        return this.dogStatus;
    }
}















