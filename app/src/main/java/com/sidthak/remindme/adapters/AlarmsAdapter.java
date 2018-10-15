package com.sidthak.remindme.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sidthak.remindme.R;
import com.sidthak.remindme.activities.SetAlarmActivity;
import com.sidthak.remindme.models.AlarmModel;
import com.sidthak.remindme.models.WishModel;

import java.util.List;

public class AlarmsAdapter extends ArrayAdapter<AlarmModel> {


    public AlarmsAdapter(@NonNull Context context, @NonNull List<AlarmModel> objects) {
        super(context, R.layout.alarm_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        AlarmModel alarm = getItem(position);

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.alarm_item, null);
        }

        CardView abc = v.findViewById(R.id.card_view);
        TextView mClock = v.findViewById(R.id.alarm_time);
        TextView mSwitch = v.findViewById(R.id.alarm_switch);

        abc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SetAlarmActivity.class);
                getContext().startActivity(i);
            }
        });

//        TextView mTitle = v.findViewById(R.id.title);
//        TextView mDescription = v.findViewById(R.id.description);
//
//        mTitle.setText(wish.getTitle());
//        mDescription.setText(wish.getDescription());

        return v;
    }
}

