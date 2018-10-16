package com.sidthak.remindme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sidthak.remindme.R;
import com.touchboarder.weekdaysbuttons.WeekdaysDataItem;
import com.touchboarder.weekdaysbuttons.WeekdaysDataSource;

import java.util.ArrayList;
import java.util.Calendar;

public class SetAlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        WeekdaysDataSource wds = new WeekdaysDataSource(this, R.id.weekdays_stub)
                .start(new WeekdaysDataSource.Callback() {
                    @Override
                    public void onWeekdaysItemClicked(int attachId,WeekdaysDataItem item) {
                        // Do something if today is selected?
                        Calendar calendar = Calendar.getInstance();
                        if(item.getCalendarDayId()==calendar.get(Calendar.DAY_OF_WEEK)&&item.isSelected())
                            Toast.makeText(SetAlarmActivity.this,"Carpe diem",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onWeekdaysSelected(int attachId,ArrayList<WeekdaysDataItem> items) {
                        //Filter on the attached id if there is multiple weekdays data sources
                       // if(attachId==R.id.weekdays_stub_4){
                            // Do something on week 4?
                        //}
                    }

                });
    }
}
