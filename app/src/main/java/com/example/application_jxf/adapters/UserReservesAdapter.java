package com.example.application_jxf.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.application_jxf.R;
import com.example.application_jxf.pojo.BookingItem;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class UserReservesAdapter extends RecyclerView.Adapter<UserReservesAdapter.BookingHolder> {

    private List<BookingItem> list;
    private LayoutInflater inflater;

    public UserReservesAdapter(List<BookingItem> list, Context ctx) {
        this.list = list;
        inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.booking_item, viewGroup, false);
        return new BookingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHolder bookingHolder, int i) {
        BookingItem item = list.get(i);
        long timeTo = item.getHour()+item.getDuration();
        String bookingTime = item.getBookingDate().toLocaleString() + " From " + item.getHour() + " To " + timeTo;
        String bookingName = item.getRestourantName() + " table #" + item.getTableNumber();
        bookingHolder.bookingTime.setText(bookingTime);
        bookingHolder.bookingName.setText(bookingName);
        Picasso.get().load(item.getUrl())
                .centerInside().fit().into(bookingHolder.img);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class BookingHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView bookingName;
        TextView bookingTime;
        Button button;

        public BookingHolder(@NonNull View itemView) {
            super(itemView);
            bookingName = itemView.findViewById(R.id.bookingName);
            bookingTime = itemView.findViewById(R.id.bookingTime);
            img = itemView.findViewById(R.id.booking_image);

        }
    }
}
