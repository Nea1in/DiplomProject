package com.anthony.marco.balljumpgame;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private BallJumpView gameView;
    private Handler handler = new Handler();
    private final static long interval = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        startService(new Intent(this, MyService.class));
        gameView = new BallJumpView(this);
        setContentView(gameView);



      /*  int duration = Toast.LENGTH_LONG;
        Toast toast2 = Toast.makeText(getApplicationContext(),
               "press the right side of the screen to move to the right",
                duration);
        toast2.setGravity(Gravity.CENTER, 600, 0);



        Toast toast = Toast.makeText(getApplicationContext(),
                "press the left side of the screen to move to the left",
                duration);
        toast.setGravity(Gravity.CENTER, 0, 600);
        toast2.show();
        toast.show();*/

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, interval);
    }
}


