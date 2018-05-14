package com.example.thymen.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity implements HighscoreHelper.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        ListView listView = findViewById(R.id.highscoreList);
        HighscoreActivity highscoreActivity = new HighscoreActivity();
        highscoreActivity.
    }

    public void gotHighscores(ArrayList<Highscore> highscores) {
        ArrayAdapter<Highscore> adapter = new HighscoresAdapter(this, R.layout.adapter_higscore, highscores);
    }

    @Override
    public void gotError(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }
}
