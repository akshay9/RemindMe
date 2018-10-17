package com.sidthak.remindme.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sidthak.remindme.MainActivity;
import com.sidthak.remindme.R;
import com.sidthak.remindme.models.AlarmModel;
import com.sidthak.remindme.models.UserModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button login =findViewById(R.id.login);
        Button register=findViewById(R.id.register);
        final EditText username=findViewById(R.id.username);
        final EditText password=findViewById(R.id.password);

        final SharedPreferences sharedPref = getSharedPreferences(
                "remindMe", Context.MODE_PRIVATE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference colRef = FirebaseFirestore.getInstance().collection("users");
                colRef.whereEqualTo("username", username.getText().toString())
                        .whereEqualTo("password", password.getText().toString())
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(task.getResult().size() > 0){
                                sharedPref.edit().putString("username", username.getText().toString()).commit();
                                Intent i=new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                LoginActivity.this.finish();
                            } else {
                                //Failed
                                Toast.makeText(LoginActivity.this, "Wrong id or pass", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
            }
        });
    }
}
