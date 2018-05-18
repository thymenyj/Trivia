package com.example.thymen.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MultiplayerGameActivity extends AppCompatActivity implements TriviaHelper.Callback{
    private TextView questionLine;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    public Button quit;
    private String correct;

    boolean turn = false;
    public int scoreCount1;
    public int scoreCount2;

    public TextView playerTurn, counter;
    public String player1, player2;

    public int gameCounter, startAmount, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_game);

        Bundle extra = getIntent().getBundleExtra("bundle");
        if (!extra.isEmpty()) {
            startAmount = extra.getInt("amount");
        }

        gameCounter = startAmount;
        player1 = "Player 1";
        player2 = "Player 2";
        scoreCount1 = 0;
        scoreCount2 = 0;

        playerTurn = findViewById(R.id.playerTurn);
        playerTurn.setText(player1);
        counter = findViewById(R.id.counter);
        counter.setText("" + gameCounter);

        questionLine = findViewById(R.id.question);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        quit = findViewById(R.id.quit);

        TriviaHelper triviaHelper = new TriviaHelper(this);
        triviaHelper.getNextQuestion(this);

        // if answer 1 clicked check if answer is correct
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer1.getText() == correct) {
                    Toast.makeText(MultiplayerGameActivity.this, "correct", Toast.LENGTH_SHORT).show();
                    updateScore();
                }
                else {
                    Toast.makeText(MultiplayerGameActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                updatePlayerTurn();
                TriviaHelper triviaHelper = new TriviaHelper(getApplicationContext());
                triviaHelper.getNextQuestion(MultiplayerGameActivity.this);
            }
        });

        // if answer 2 clicked check if answer is correct
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer2.getText() == correct) {
                    Toast.makeText(MultiplayerGameActivity.this, "correct", Toast.LENGTH_SHORT).show();
                    updateScore();
                }
                else {
                    Toast.makeText(MultiplayerGameActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                updatePlayerTurn();
                TriviaHelper triviaHelper = new TriviaHelper(getApplicationContext());
                triviaHelper.getNextQuestion(MultiplayerGameActivity.this);
            }
        });

        // if answer 1 clicked check if answer is correct
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer3.getText() == correct) {
                    Toast.makeText(MultiplayerGameActivity.this, "correct", Toast.LENGTH_SHORT).show();
                    updateScore();
                }
                else {
                    Toast.makeText(MultiplayerGameActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                updatePlayerTurn();
                TriviaHelper triviaHelper = new TriviaHelper(getApplicationContext());
                triviaHelper.getNextQuestion(MultiplayerGameActivity.this);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiplayerGameActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }

    public void gotQuestion(Question question) {
        // update correct variable to check answersClicked
        correct = question.getCorrectAnswer();

        // set question textview
        questionLine.setText(question.getQuestion());

        // set answer buttons
        ArrayList<String> answers = question.getAnswers();
        answer1.setText(answers.get(0));
        answer2.setText(answers.get(1));
        answer3.setText(answers.get(2));

    }

    @Override
    public void gotError(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }

    public void updatePlayerTurn() {
        if (!turn) {
            turn = true;
            playerTurn.setText(player2);
        }
        else {
            turn = false;
            playerTurn.setText(player1);
        }
        gameCounter = gameCounter - 1;
        counter.setText("" + gameCounter);
        if (gameCounter == 0) {
            Intent intent = new Intent(MultiplayerGameActivity.this, MultiplayerScore.class);
            Bundle bundle = new Bundle();
            bundle.putInt("score1", scoreCount1);
            bundle.putInt("score2", scoreCount2);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        }
    }

    public void updateScore() {
        if (!turn) {
            scoreCount1 = scoreCount1 + 1;
        }
        else {
            scoreCount2 = scoreCount2 + 1;
        }
    }

}

