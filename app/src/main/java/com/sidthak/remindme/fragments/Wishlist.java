package com.sidthak.remindme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sidthak.remindme.R;
import com.sidthak.remindme.activities.WishListFormActivity;
import com.sidthak.remindme.adapters.WishListAdapter;
import com.sidthak.remindme.models.WishModel;

import java.util.ArrayList;


public class Wishlist extends Fragment {

    ArrayList<WishModel> wishes;
    FirebaseFirestore db;
    ArrayAdapter adapter;

    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.

        View rootView = inflater.inflate(
                R.layout.fragment_wishlist, container, false);
        ListView mListView = rootView.findViewById(R.id.lv_wishlist);

        wishes = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        getWishes();

        adapter=new WishListAdapter(getActivity(), wishes);
        mListView.setAdapter(adapter);

        FloatingActionButton form= rootView.findViewById(R.id.fab);
        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getContext(),WishListFormActivity.class);
                getContext().startActivity(i);
            }
        });
        Bundle args = getArguments();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getWishes();
    }

    private void getWishes() {
        CollectionReference colRef = db.collection("wishes");
        colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    wishes.clear();
                    for (DocumentSnapshot document : task.getResult()) {
                        wishes.add(document.toObject(WishModel.class));
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    //Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }
}
