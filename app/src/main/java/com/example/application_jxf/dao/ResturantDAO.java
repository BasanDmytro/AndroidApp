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
//        TODO rename
        restourantItems.add(new RestourantItem(1,-34, 151, "Ushamila", "luchaya shashlichka v sidneye", PHOTO_URL));
        restourantItems.add(new RestourantItem(2,-34.0001, 151, "Ushamila", "luchaya shashlichka v sidneye", PHOTO_URL));
        restourantItems.add(new RestourantItem(3,-34.0005, 151.0007, "Ushamila", "luchaya shashlichka v sidneye", PHOTO_URL));
        restourantItems.add(new RestourantItem(0,-34.0003, 151.0004, "Ushamila", "luchaya shashlichka v sidneye", PHOTO_URL));
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
                    if (bi.getBookingDate().equals(bookingItem.getBookingDate())) {
                        if (bi.getTableNumber() == bookingItem.getTableNumber()) {
//                            TODO change formula
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
