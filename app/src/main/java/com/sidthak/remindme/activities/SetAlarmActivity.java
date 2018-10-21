package com.sidthak.remindme.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sidthak.remindme.R;
import com.sidthak.remindme.models.AlarmModel;
import com.touchboarder.weekdaysbuttons.WeekdaysDataItem;
import com.touchboarder.weekdaysbuttons.WeekdaysDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SetAlarmActivity extends AppCompatActivity {

    TimePicker mTime;
    WeekdaysDataSource wds;
    TextView alarmName, alarmDescription;
    Button mSubmit, mCancel;
    List<Boolean> days;
    boolean repeat = false;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        sharedPref = getSharedPreferences(
                "remindMe", Context.MODE_PRIVATE);

        days= new ArrayList<>(8);
        days.add(false);
        for(int i=0; i<=7; i++){
            days.add(false);
        }
        mTime = findViewById(R.id.timePicker1);
        mTime.setIs24HourView(true);
        wds = new WeekdaysDataSource(this, R.id.weekdays_stub)
                .start(weekdaysDataCallback);

        alarmName = findViewById(R.id.alarm_actual_name);
        alarmDescription = findViewById(R.id.alarm_actual_description);

        mSubmit = findViewById(R.id.submit_alarm);
        mCancel = findViewById(R.id.cancel_alarm);

        Intent callingIntent = getIntent();

        if(callingIntent.hasExtra("alarm")){
            populateFields((AlarmModel)callingIntent.getSerializableExtra("alarm"));
        }

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetAlarmActivity.this.finish();
            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmModel alarm = new AlarmModel(sharedPref.getString("username", "akshay"),
                        alarmName.getText().toString(),
                        alarmDescription.getText().toString(),
                        mTime.getCurrentHour(),
                        mTime.getCurrentMinute(),
                        days,
                        repeat,
                        true
                        );
                alarm.addToFireStore();
                SetAlarmActivity.this.finish();
            }
        });
    }

    private void populateFields(AlarmModel alarm){
        alarmName.setText(alarm.getName());
        alarmDescription.setText(alarm.getDescription());
        mTime.setCurrentHour(alarm.getHours());
        mTime.setCurrentMinute(alarm.getMinutes());
        days = alarm.getDays();
        repeat = alarm.isRepeat();

        List<Integer> wdsdays = new ArrayList<>();
        Iterator<Boolean> iterator1 = days.iterator();
        iterator1.next();
        for(int i=1; i<=7; i++){

            boolean temp = iterator1.next();
            if(temp == true){
                wdsdays.add(i-1);
            }
        }
        int[] ret = new int[wdsdays.size()];
        Iterator<Integer> iterator = wdsdays.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next();
        }
        wds.setSelectedDays(ret);
    }

    WeekdaysDataSource.Callback weekdaysDataCallback = new WeekdaysDataSource.Callback() {
        @Override
        public void onWeekdaysItemClicked(int attachId,WeekdaysDataItem item) {
            // Do something if today is selected?
//            Calendar calendar = Calendar.getInstance();
//            if(item.getCalendarDayId()==calendar.get(Calendar.DAY_OF_WEEK) && item.isSelected())
//                Toast.makeText(SetAlarmActivity.this,"Carpe diem",Toast.LENGTH_SHORT).show();
            days.set(item.getCalendarDayId(), !days.get(item.getCalendarDayId()));
        }

        @Override
        public void onWeekdaysSelected(int attachId,ArrayList<WeekdaysDataItem> items) {
            //Filter on the attached id if there is multiple weekdays data sources
            // if(attachId==R.id.weekdays_stub_4){
            // Do something on week 4?
            //}
        }

    };
}
