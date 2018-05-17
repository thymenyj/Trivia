package com.example.thymen.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MultiplayerStartActivity extends AppCompatActivity {
    public Button startGame;
    public EditText namePlayer1, namePlayer2, namePlayer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_start);

        startGame = findViewById(R.id.startGame);
        namePlayer1 = findViewById(R.id.namePlayer1);
        namePlayer2 = findViewById(R.id.namePlayer2);
        namePlayer3 = findViewById(R.id.namePlayer3);


    }
}
