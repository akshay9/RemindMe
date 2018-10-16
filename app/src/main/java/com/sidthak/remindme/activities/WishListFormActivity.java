package com.sidthak.remindme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sidthak.remindme.R;
import com.sidthak.remindme.models.WishModel;

public class WishListFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_form);

        final EditText mWishName = findViewById(R.id.wish_name);
        final EditText mWishDescription = findViewById(R.id.wish_description);
        Button mSubmit = findViewById(R.id.submit_wish);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishModel wish = new WishModel(mWishName.getText().toString(),
                        mWishDescription.getText().toString(), "akshay");
                wish.addToFireStore();
                WishListFormActivity.this.finish();
            }
        });
    }
}
