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
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.sidthak.remindme.R;
import com.sidthak.remindme.activities.SetAlarmActivity;
import com.sidthak.remindme.models.AlarmModel;
import com.sidthak.remindme.models.WishModel;

import java.text.DecimalFormat;
import java.util.List;

public class AlarmsAdapter extends ArrayAdapter<AlarmModel> {


    public AlarmsAdapter(@NonNull Context context, @NonNull List<AlarmModel> objects) {
        super(context, R.layout.alarm_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        final AlarmModel alarm = getItem(position);

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.alarm_item, null);
        }

        CardView abc = v.findViewById(R.id.card_view);
        TextView mClock = v.findViewById(R.id.alarm_time);
        Switch mSwitch = v.findViewById(R.id.alarm_switch);
        TextView mTitle = v.findViewById(R.id.alarm_card_title);


        String clockText = new DecimalFormat("00").format(alarm.getHours()) + ":"
                + new DecimalFormat("00").format(alarm.getMinutes());
        mClock.setText(clockText);
        mSwitch.setChecked(alarm.isActivated());
        mTitle.setText(alarm.getName());

        abc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SetAlarmActivity.class);
                i.putExtra("alarm", alarm);
                getContext().startActivity(i);
            }
        });

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO: update firestore;
            }
        });

        return v;
    }
}

