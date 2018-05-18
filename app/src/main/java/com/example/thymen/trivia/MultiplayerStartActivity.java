package com.example.thymen.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MultiplayerStartActivity extends AppCompatActivity {
    public int amount;
    public EditText count;
    public Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_start);

        count = findViewById(R.id.amount);
        startGame = findViewById(R.id.startGame);


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!count.getText().toString().isEmpty()) {
                    String number = count.getText().toString();
                    amount = Integer.parseInt(number);
                }
                else {
                    amount = 10;
                }
                Intent intent = new Intent(MultiplayerStartActivity.this, MultiplayerGameActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("amount", amount);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }
}
