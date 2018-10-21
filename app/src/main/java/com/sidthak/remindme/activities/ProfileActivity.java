package com.sidthak.remindme.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sidthak.remindme.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPref = getSharedPreferences(
                "remindMe", Context.MODE_PRIVATE);

        TextView mName = findViewById(R.id.profile_name);
        mName.setText(sharedPref.getString("username", "no name found"));
    }
}
