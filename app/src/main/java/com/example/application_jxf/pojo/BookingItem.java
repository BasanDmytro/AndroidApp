package com.example.application_jxf.pojo;

import android.media.Image;

import java.util.Date;

public class BookingItem {
    private String name;
    private String userId;
    private Date bookingDate;
    private String url;

    public BookingItem(String name, String userId, Date bookingDate, String url) {
        this.name = name;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BookingItem{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", bookingDate=" + bookingDate +
                ", url='" + url + '\'' +
                '}';
    }
}
