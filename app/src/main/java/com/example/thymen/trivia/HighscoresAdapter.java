package com.example.thymen.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HighscoresAdapter extends ArrayAdapter<Highscore> {

    private ArrayList<Highscore> list;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Highscore object = list.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_higscore, parent, false);
        }
        TextView name = convertView.findViewById(R.id.nameItem);
        TextView score = convertView.findViewById(R.id.scoreItem);

        name.setText(object.getName());
        String scoreString = "" + object.getScore();
        score.setText(scoreString);

        return convertView;
    }

    public HighscoresAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Highscore> objects) {
        super(context, resource, objects);

        list = objects;

    }
}

