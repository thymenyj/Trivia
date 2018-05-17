package com.example.thymen.trivia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements TriviaHelper.Callback{
    private TextView questionLine;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    public Button quit;
    private String correct;

    public TextView score;
    public int scoreCount;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        database = FirebaseDatabase.getInstance().getReference();

        // set score to zero when game starts
        scoreCount = 0;

        questionLine = findViewById(R.id.question);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        quit = findViewById(R.id.quit);

        score = findViewById(R.id.numberScore);

        TriviaHelper triviaHelper = new TriviaHelper(this);
        triviaHelper.getNextQuestion(this);

        // if answer 1 clicked check if answer is correct
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer1.getText() == correct) {
                    scoreCount = scoreCount + 1;
                    score.setText("" + scoreCount);
                    Toast.makeText(GameActivity.this, "correct", Toast.LENGTH_SHORT).show();
                    TriviaHelper triviaHelper = new TriviaHelper(getApplicationContext());
                    triviaHelper.getNextQuestion(GameActivity.this);
                }
                else {
                    Toast.makeText(GameActivity.this, "wrong, game over", Toast.LENGTH_SHORT).show();
                    updateHighscore();
                    Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                    startActivity(intent);
                }

            }
        });

        // if answer 2 clicked check if answer is correct
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer2.getText() == correct) {
                    scoreCount = scoreCount + 1;
                    score.setText("" + scoreCount);
                    Toast.makeText(GameActivity.this, "correct", Toast.LENGTH_SHORT).show();
                    TriviaHelper triviaHelper = new TriviaHelper(getApplicationContext());
                    triviaHelper.getNextQuestion(GameActivity.this);
                }
                else {
                    Toast.makeText(GameActivity.this, "wrong, game over", Toast.LENGTH_SHORT).show();
                    updateHighscore();
                    Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            }
        });

        // if answer 1 clicked check if answer is correct
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer3.getText() == correct) {
                    scoreCount = scoreCount + 1;
                    score.setText("" + scoreCount);
                    Toast.makeText(GameActivity.this, "correct", Toast.LENGTH_SHORT).show();
                    TriviaHelper triviaHelper = new TriviaHelper(getApplicationContext());
                    triviaHelper.getNextQuestion(GameActivity.this);
                }
                else {
                    Toast.makeText(GameActivity.this, "wrong, game over", Toast.LENGTH_SHORT).show();
                    updateHighscore();
                    Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueEventListener postListener = new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // check highscore in database
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String userId = user.getUid();

                        Highscore highscoreItem = dataSnapshot.child("highscores").child(userId).getValue(Highscore.class);
                        int highscore = highscoreItem.getScore();
                        String name = highscoreItem.getName();
                        // if current score is higher update highscore user
                        if (scoreCount > highscore) {
                            Highscore update = new Highscore();
                            update.setName(name);
                            update.setScore(scoreCount);
                            database.child("highscores").child(userId).setValue(update);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("TAG", "something went wrong", databaseError.toException());
                    }
                };
                database.addValueEventListener(postListener);

            Intent intent = new Intent(GameActivity.this, MenuActivity.class);
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

    public void updateHighscore() {
        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // check highscore in database
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userId = user.getUid();

                Highscore highscoreItem = dataSnapshot.child("highscores").child(userId).getValue(Highscore.class);
                int highscore = highscoreItem.getScore();
                String name = highscoreItem.getName();

                // if current score is higher update highscore user
                if (scoreCount > highscore) {
                    Highscore update = new Highscore();
                    update.setName(name);
                    update.setScore(scoreCount);
                    database.child("highscores").child(userId).setValue(update);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", "something went wrong", databaseError.toException());
            }
        };
        database.addValueEventListener(postListener);

        Intent intent = new Intent(GameActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
