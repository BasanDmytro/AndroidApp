package com.example.application_jxf.pojo;

import android.media.Image;

import java.util.Date;

public class BookingItem {
    private String restourantName;
    private int hour;
    private int duration;
    private Date bookingDate;
    private int tableNumber;
    private String url;

    public BookingItem(String restourantName, int hour, int duration, Date bookingDate, int tableNumber, String url) {
        this.restourantName = restourantName;
        this.hour = hour;
        this.duration = duration;
        this.bookingDate = bookingDate;
        this.tableNumber = tableNumber;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRestourantName() {
        return restourantName;
    }

    public void setRestourantName(String restourantName) {
        this.restourantName = restourantName;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "BookingItem{" +
                "restourantName='" + restourantName + '\'' +
                ", hour=" + hour +
                ", duration=" + duration +
                ", bookingDate=" + bookingDate +
                ", tableNumber=" + tableNumber +
                ", url='" + url + '\'' +
                '}';
    }
}
