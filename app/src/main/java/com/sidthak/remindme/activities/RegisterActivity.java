package com.sidthak.remindme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.sidthak.remindme.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText username=findViewById(R.id.username);
        EditText Password=findViewById(R.id.password);
        EditText age=findViewById(R.id.age);
        EditText emailid=findViewById(R.id.email);
    }
}
