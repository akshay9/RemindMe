package com.sidthak.remindme.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sidthak.remindme.R;
import com.sidthak.remindme.activities.SetAlarmActivity;
import com.sidthak.remindme.adapters.AlarmsAdapter;
import com.sidthak.remindme.models.AlarmModel;
import com.sidthak.remindme.models.WishModel;

import java.util.ArrayList;

public class AlarmsFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    FirebaseFirestore db;
    ArrayList<AlarmModel> alarms;
    AlarmsAdapter adapter;
    SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_alarms, container, false);
        Bundle args = getArguments();
        ListView mListView = rootView.findViewById(R.id.lv_alarms);
        FloatingActionButton mAddButton = rootView.findViewById(R.id.fab);

        sharedPref = getActivity().getSharedPreferences(
                "remindMe", Context.MODE_PRIVATE);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SetAlarmActivity.class);
                getContext().startActivity(i);
            }
        });

        db = FirebaseFirestore.getInstance();
        alarms = new ArrayList<>();

        //alarms.add(new AlarmModel());
        //getAlarms();

        adapter=new AlarmsAdapter(getActivity(), alarms);
        mListView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAlarms();
    }

    private void getAlarms() {
        CollectionReference colRef = db.collection("alarms");
        colRef.whereEqualTo("owner", sharedPref.getString("username", "akshay"))
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    alarms.clear();
                    for (DocumentSnapshot document : task.getResult()) {
                        alarms.add(document.toObject(AlarmModel.class));
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    //Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }
}
