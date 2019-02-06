package com.example.application_jxf.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.application_jxf.R;

import java.util.List;

public class UserReservesAdapter extends RecyclerView.Adapter<UserReservesAdapter.UserHolder> {

    private List<String> list;
    private LayoutInflater inflater;

    public UserReservesAdapter(List<String> list, Context ctx) {
        this.list = list;
        inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.user_item, viewGroup, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, int i) {
        userHolder.text.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView text;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
