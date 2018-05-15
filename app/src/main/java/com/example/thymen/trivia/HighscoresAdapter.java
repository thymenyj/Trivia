//package com.example.thymen.trivia;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class HighscoresAdapter extends ArrayAdapter<Highscore>{
//    private ArrayList<Highscore> highscoreList;
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        Highscore listItem = highscoreList.get(position);
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_higscore, parent, false);
//        }
//        TextView name = convertView.findViewById(R.id.highscoreName);
//        TextView score = convertView.findViewById(R.id.highscoreNumber;
//
//        name.setText(listItem.getName());
//        score.setText(listItem.getScore());
//
//        return convertView;
//    }
//
//    public HighscoresAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Highscore> objects) {
//        super(context, resource, objects);
//
//    highscoreList = objects;
//
//    }
//}
