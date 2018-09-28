package com.dev.ap.poject101.Activities;

public class ritual {
    String name;
    String day;
    int hour;
    int min;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ritual(String name,String day,int hour,int min)
    {
        this.name = name;
        this.day = day;
        this.hour = hour;
        this.min = min;
    }


}
