package com.example.thymen.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighscoreActivity extends AppCompatActivity{
    public DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        database = FirebaseDatabase.getInstance().getReference();

        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Highscore> highscoreArrayList = new ArrayList<>();

                // get data from database
                DataSnapshot highescoresSnapshot = dataSnapshot.child("highscores");
                Iterable<DataSnapshot> highscoresChildren = highescoresSnapshot.getChildren();

                // iterate over all highscores and add them to the arraylist
                for (DataSnapshot highescores : highscoresChildren) {
                    Highscore item = highescores.getValue(Highscore.class);
                    highscoreArrayList.add(item);
                }

                // order highscore list highest on top
                Collections.sort(highscoreArrayList, new Comparator<Highscore>(){
                    public int compare(Highscore self,  Highscore other) {
                         return Integer.valueOf(other.getScore()).compareTo(self.getScore());
                    }
                });

                // connect custom adapter to listview
                ArrayAdapter<Highscore> adapter = new HighscoresAdapter(HighscoreActivity.this, R.layout.adapter_higscore, highscoreArrayList);
                ListView listView = findViewById(R.id.highscoreList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", "something went wrong", databaseError.toException());
            }
        };
        database.addValueEventListener(postListener);
    }
}






