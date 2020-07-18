package com.anthony.marco.balljumpgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

public class GameOverActivity extends AppCompatActivity {

    private Button StartGameAgain;
    private TextView DisplayScore;
    private String score;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("score")).toString();

        StartGameAgain = (Button) findViewById(R.id.play_again_begin);
        DisplayScore = (TextView) findViewById(R.id.displayScore);
        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        StartGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }

        });
        stopService(new Intent(this, MyService.class));
        DisplayScore.setText("Score = " + score);
        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}
