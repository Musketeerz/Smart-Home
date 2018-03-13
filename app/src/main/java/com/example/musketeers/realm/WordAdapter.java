package com.example.musketeers.realm;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RajPrabhakar on 13-03-2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Word currentWord = getItem(position);
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView appliance = (TextView) listItemView.findViewById(R.id.applicance);
        appliance.setText(currentWord.getwAppliance());

        ImageView mImageView = (ImageView) listItemView.findViewById(R.id.icon);
        mImageView.setImageResource(currentWord.getwImage());

        Switch run = (Switch) listItemView.findViewById(R.id.switch1);
        run.setChecked(currentWord.getwRun());
        return listItemView;
    }
}
