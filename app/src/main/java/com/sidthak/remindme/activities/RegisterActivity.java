package com.sidthak.remindme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sidthak.remindme.R;
import com.sidthak.remindme.models.UserModel;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText username=findViewById(R.id.username);
        final EditText password=findViewById(R.id.password);
        final EditText age=findViewById(R.id.age);
        final EditText emailid=findViewById(R.id.email);
        Button mRegister = findViewById(R.id.register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel user = new UserModel(username.getText().toString(),
                        password.getText().toString(),
                        emailid.getText().toString(),
                        Integer.parseInt(age.getText().toString()));

                user.addToFireStore();
                RegisterActivity.this.finish();
            }
        });

    }
}
