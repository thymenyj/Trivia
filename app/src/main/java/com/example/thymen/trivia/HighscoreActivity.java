package com.example.thymen.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thymen.trivia.Highscore;
import com.example.thymen.trivia.HighscoreHelper;
import com.example.thymen.trivia.HighscoresAdapter;
import com.example.thymen.trivia.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
//                Highscore highscore = dataSnapshot.child("highscores").child("Thymen").getValue(Highscore.class);
//                highscoreArrayList.add(highscore);

                // get data from database
                DataSnapshot highescoresSnapshot = dataSnapshot.child("highscores");
                Iterable<DataSnapshot> highscoresChildren = highescoresSnapshot.getChildren();
                for (DataSnapshot highescores : highscoresChildren) {
                    Highscore item = highescores.getValue(Highscore.class);
                    highscoreArrayList.add(item);
                }
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




//        HighscoreHelper highscoreHelper= new HighscoreHelper(this);
//        highscoreHelper.getHighscores(this);

//    public void gotHighscores(ArrayList<Highscore> highscores) {
//        ArrayAdapter<Highscore> adapter = new HighscoresAdapter(this, R.layout.adapter_higscore, highscores);
//        ListView listView = findViewById(R.id.highscoreList);
//        listView.setAdapter(adapter);
//    }
//
//    @Override
//    public void gotError(String message){
//        Toast.makeText(getApplicationContext(), message,
//                Toast.LENGTH_LONG).show();
//    }





