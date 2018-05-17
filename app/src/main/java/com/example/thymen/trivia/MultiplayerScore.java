package com.example.thymen.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MultiplayerScore extends AppCompatActivity {
    public String winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_score);

        Bundle extra = getIntent().getBundleExtra("bundle");
        int score1 = extra.getInt("score1");
        int score2 = extra.getInt("score2");

        if (score1 > score2){
            winner = "PLAYER 1 WON";
        }
        else if (score2 > score1) {
            winner = "PLAYER 2 WON";
        }
        else {
            winner = "DRAW";
        }

        TextView winnerTextview = findViewById(R.id.winner);
        TextView player1Score = findViewById(R.id.player1Score);
        TextView player2Score = findViewById(R.id.player2Score);
        Button playAgain = findViewById(R.id.playAgain);
        Button backMenu = findViewById(R.id.backMenu);


        player1Score.setText("Player 1: " + score1);
        player2Score.setText("Player 2: " + score2);
        winnerTextview.setText(winner);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiplayerScore.this, MultiplayerStartActivity.class);
                startActivity(intent);
            }
        });

        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiplayerScore.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
