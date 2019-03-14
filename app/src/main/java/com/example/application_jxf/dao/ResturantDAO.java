package com.example.application_jxf.dao;

import com.example.application_jxf.pojo.BookingItem;
import com.example.application_jxf.pojo.RestourantItem;

import java.util.ArrayList;
import java.util.List;

public class ResturantDAO {
    private static final ResturantDAO ourInstance = new ResturantDAO();

    public static ResturantDAO getInstance() {
        return ourInstance;
    }

    private List<RestourantItem> restourantItems = new ArrayList<>();
    private List<BookingItem> bookingItems = new ArrayList<>();
    public static final String PHOTO_URL = "https://www.worlds50bestbars.com/files/Listing/Images/1//American_Bar-Museum_Bar_2.jpg";

    private ResturantDAO() {
        restourantItems.add(new RestourantItem(1,48.015, 0.166, "Pizza", "Best pizza in the city", PHOTO_URL));
        restourantItems.add(new RestourantItem(2,48.016, 0.167, "Bar", "Good Irish bar for relax or party", PHOTO_URL));
        restourantItems.add(new RestourantItem(3,48.017, 0.169, "Night Bar", "The best night club in city with famous DJ", PHOTO_URL));
        restourantItems.add(new RestourantItem(0,48.015, 0.171, "Cafe", "Cafe with pretty good espresso", PHOTO_URL));
    }

    public List<RestourantItem> getRestourantItems() {
        return restourantItems;
    }

    public List<BookingItem> getBookingItems() {
        return bookingItems;
    }

    public boolean addBookingItem(BookingItem bookingItem){
        if (!bookingItems.isEmpty()) {
            for (BookingItem bi : bookingItems) {
                if (bi.getRestourantName().equals(bookingItem.getRestourantName())) {
                    if (bi.getBookingDate().getYear() == bookingItem.getBookingDate().getYear()
                            && bi.getBookingDate().getMonth() == bookingItem.getBookingDate().getMonth()
                            && bi.getBookingDate().getDay() == bookingItem.getBookingDate().getDay()) {
                        if (bi.getTableNumber() == bookingItem.getTableNumber()) {
                            if ((bi.getHour() >= bookingItem.getHour() && bi.getHour() <= bookingItem.getHour() + bookingItem.getDuration())
                                    || bi.getHour() <= bookingItem.getHour() && bi.getHour() + bi.getDuration() >= bookingItem.getHour()) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return bookingItems.add(bookingItem);

    }

    public void removeBookingItem(BookingItem bookingItem){
        if (!bookingItems.isEmpty()){
            bookingItems.remove(bookingItem);
        }
    }
}
