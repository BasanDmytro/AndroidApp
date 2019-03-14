package com.example.application_jxf.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.application_jxf.R;
import com.example.application_jxf.dao.ResturantDAO;
import com.example.application_jxf.misc.ColorTool;
import com.example.application_jxf.pojo.BookingItem;
import com.example.application_jxf.pojo.RestourantItem;

import java.util.Date;

public class BookingActivity extends AppCompatActivity
        implements View.OnTouchListener {

    int DIALOG_DATE = 1;
    int DIALOG_TIME = 2;
    int DIALOG_PERIOD = 3;
    int DIALOG_CONFIRM = 4;
    int DIALOG_SUCCESS = 5;
    int DIALOG_FAIL = 6;
    int cstYear = 2019;
    int cstMonth = 2;
    int cstDay = 14;

    BookingItem item = new BookingItem();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("id", 0);
        if (id == 0 || id % 2 == 0) {
            setContentView(R.layout.activity_boking_two);
        } else {
            setContentView(R.layout.activity_boking_one);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Booking");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView iv = (ImageView) findViewById(R.id.image);
        if (iv != null) {
            iv.setOnTouchListener(this);
        }
        RestourantItem restourantItem = ResturantDAO.getInstance().getRestourantItems().get(id);
        item.setRestourantName(restourantItem.getTitle());
        item.setUrl(restourantItem.getUrl());
    }

    @Override
    public boolean onTouch(View v, MotionEvent ev) {
        v.performClick();
        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();

        ImageView imageView = (ImageView) v.findViewById(R.id.image);
        if (imageView == null) return false;

        switch (action) {
            case MotionEvent.ACTION_UP:
                int touchColor = getHotspotColor(R.id.image_areas, evX, evY);
                ColorTool ct = new ColorTool();
                int tolerance = 25;
                if (ct.closeMatch(-16501763, touchColor, tolerance)) {
                    Toast.makeText(this, "table 1", Toast.LENGTH_SHORT).show();
                    item.setTableNumber(1);
                } else if (ct.closeMatch(-1956862, touchColor, tolerance)) {
                    Toast.makeText(this, "table 2", Toast.LENGTH_SHORT).show();
                    item.setTableNumber(2);
                } else if (ct.closeMatch(-14590, touchColor, tolerance)) {
                    Toast.makeText(this, "table 3", Toast.LENGTH_SHORT).show();
                    item.setTableNumber(3);
                } else if (ct.closeMatch(Color.GREEN, touchColor, tolerance)) {
                    Toast.makeText(this, "table 4", Toast.LENGTH_SHORT).show();
                    item.setTableNumber(4);
                } else if (ct.closeMatch(Color.BLACK, touchColor, tolerance)) {
                    Toast.makeText(this, "table 5", Toast.LENGTH_SHORT).show();
                    item.setTableNumber(5);
                }

                showDialog(DIALOG_DATE);

                break;

            default:
        }
        return true;
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, dateDialogCallBack, cstYear, cstMonth, cstDay);
            return tpd;
        } else if (id == DIALOG_TIME){
            TimePickerDialog tpd = new TimePickerDialog(this, timeDialogCallBack, 00, 00, true);
            return tpd;
        } else if (id == DIALOG_PERIOD){
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Input duration");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            adb.setView(input);
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    item.setDuration(Integer.parseInt(input.getText().toString()));
                    showDialog(DIALOG_CONFIRM);
                }
            });
            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            adb.show();
        } else if (id == DIALOG_CONFIRM) {
            String s = item.getRestourantName() + " table #" + item.getTableNumber()
                    + "\nOn " + item.getBookingDate().getDay() + "."
                    + item.getBookingDate().getMonth() + "." + item.getBookingDate().getYear()
                    + " " + item.getHour() + ":00" + "for " + item.getDuration()
                    + " hours.";
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Confirm booking?");
            adb.setMessage(s);
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    add item to db
                    if (ResturantDAO.getInstance().addBookingItem(item)){
                        showDialog(DIALOG_SUCCESS);
                    } else {
                        showDialog(DIALOG_FAIL);
                    }
                }
            });
            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            adb.show();
        } else if (id == DIALOG_SUCCESS) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Confirmation success");
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();
                }
            });
            adb.show();
        } else if (id == DIALOG_FAIL) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Booking failed!");
            adb.setMessage("Please choose another variant");
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();
                }
            });
            adb.show();
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener timeDialogCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            item.setHour(hourOfDay);
            showDialog(DIALOG_PERIOD);
        }
    };

    DatePickerDialog.OnDateSetListener dateDialogCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            Date date = new Date();
            date.setYear(year);
            date.setMonth(monthOfYear);
            date.setDate(dayOfMonth);
            item.setBookingDate(date);
            showDialog(DIALOG_TIME);
        }
    };

    public int getHotspotColor(int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById(hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
