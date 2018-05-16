package com.example.thymen.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighscoreHelper {
    public Context context;
    public Callback activity;

    private DatabaseReference database;

    public interface Callback {
        void gotHighscores(ArrayList<Highscore> highscores);
        void gotError(String message);
    }

    public HighscoreHelper(Context context) {
        this.context = context;
    }

//    public void postNewHighscore() {
//        database = FirebaseDatabase.getInstance().getReference();
//        database.setLogLevel(Logger.Level.DEBUG);
//
//        DatabaseReference myRef = database.getReference("highscores");
//        myRef.setValue("");
//    }

    public void getHighscores(final Callback activity) {
        this.activity = activity;
        ValueEventListener postListener = new ValueEventListener() {
        ArrayList<Highscore> highscoreArrayList = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // check highscore in database
                DataSnapshot highescoresSnapshot = dataSnapshot.child("highscores");
                Iterable<DataSnapshot> highscoresChildren = highescoresSnapshot.getChildren();
                for (DataSnapshot highescores : highscoresChildren) {
                    Highscore item = highescores.getValue(Highscore.class);
                    highscoreArrayList.add(item);
                }
                activity.gotHighscores(highscoreArrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", "something went wrong", databaseError.toException());
            }
        };
        database.addValueEventListener(postListener);

    }

//    public void onResponse(JSONObject response) {
//        try {
//            JSONArray array = response.getJSONArray("highscores");
//            int length = array.length();
//            ArrayList<Highscore> highscores = new ArrayList<>();
//
//            for (int i = 0; i < length; i++) {
//                Highscore highscoreItem = new Highscore();
//                JSONObject  = array.getJSONObject(i);
//
//
//        activity.gotHighscores(highscores);
//    }

    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        activity.gotError(message);
    }

}
