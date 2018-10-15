package com.sidthak.remindme.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sidthak.remindme.R;
import com.sidthak.remindme.models.WishModel;

import java.util.List;

public class WishListAdapter extends ArrayAdapter<WishModel> {

    public WishListAdapter(@NonNull Context context,  @NonNull List<WishModel> objects) {
        super(context, R.layout.wishlist_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        WishModel wish = getItem(position);

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.wishlist_item, null);
        }

        TextView mTitle = v.findViewById(R.id.title);
        TextView mDescription = v.findViewById(R.id.description);

        mTitle.setText(wish.getTitle());
        mDescription.setText(wish.getDescription());

        return v;
    }
}
