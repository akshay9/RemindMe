package com.sidthak.remindme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sidthak.remindme.R;
import com.sidthak.remindme.models.UserModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login =findViewById(R.id.login);
        Button register=findViewById(R.id.register);
        EditText username=findViewById(R.id.username);
        EditText password=findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel user = new UserModel();
                user.addToFireStore();
                LoginActivity.this.finish();
            }
        });
    }
}
