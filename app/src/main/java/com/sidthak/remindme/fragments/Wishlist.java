package com.sidthak.remindme.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sidthak.remindme.R;
import com.sidthak.remindme.adapters.WishListAdapter;
import com.sidthak.remindme.models.WishModel;

import java.util.ArrayList;

public class Wishlist extends Fragment {
    public static final String ARG_OBJECT = "object";



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_wishlist, container, false);
        ListView mListView = rootView.findViewById(R.id.lv_wishlist);

        ArrayList<WishModel> wishes = new ArrayList<>();

        wishes.add(new WishModel("title 1", "description", "Author 1"));
        wishes.add(new WishModel("title 2", "description", "Author 1"));
        wishes.add(new WishModel("title 3", "description", "Author 2"));
        wishes.add(new WishModel("title 4", "description", "Author 2"));
        wishes.add(new WishModel("title 4", "description", "Author 2"));
        wishes.add(new WishModel("title 4", "description", "Author 2"));
        wishes.add(new WishModel("title 4", "description", "Author 2"));
        wishes.add(new WishModel("title 4", "description", "Author 2"));
        wishes.add(new WishModel("title 4", "description", "Author 2"));
        wishes.add(new WishModel("title 4", "description", "Author 2"));
        wishes.add(new WishModel("title 4", "description", "Author 2"));
        wishes.add(new WishModel("title 4", "description", "Author 2"));

        ArrayAdapter adapter=new WishListAdapter(getActivity(), wishes);
        mListView.setAdapter(adapter);

//        rootView.

        Bundle args = getArguments();
        return rootView;
    }
}
