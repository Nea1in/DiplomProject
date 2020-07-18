package com.anthony.marco.balljumpgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class BallJumpView extends View {


    private static int barAmount = 11;



    int canvasWidth, canvasHeight;

    private Bitmap[] Ball = new Bitmap[2];
    int BallX = 300, BallY = 1200, BallSpeed = 0, ballSpeedAmplify = 1;
    boolean leftMove = false, rightMove = false, direction = false, isFelt = false, amp = false;

    private Bitmap backgroundImage;

    private Bitmap[] bar = new Bitmap[barAmount];
    ArrayList<Integer> barX = new ArrayList<>();
    ArrayList<Integer> barY = new ArrayList<>();

    private int score = 0;
    private Paint scorePaint = new Paint();

   // Intent gameOverIntent;


    public BallJumpView(Context context) {
        super(context);

        Ball[0] = BitmapFactory.decodeResource(getResources(), R.drawable.monster1_right_close);
        Ball[1] = BitmapFactory.decodeResource(getResources(), R.drawable.monster1_left_close);
        Ball[0] = Bitmap.createScaledBitmap(Ball[0], (int)(Ball[0].getWidth()*0.8),
                (int)(Ball[0].getHeight()*0.8), true);
        Ball[1] = Bitmap.createScaledBitmap(Ball[1], (int)(Ball[1].getWidth()*0.8),
                (int)(Ball[1].getHeight()*0.8), true);

        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background1);

        for(int i = 0; i < barAmount; i++) {
            bar[i] = BitmapFactory.decodeResource(getResources(), R.drawable.bar);
            if(i == 0){
                barX.add(400);
                barY.add(1600);
            }
            else{
                barX.add(i * 110);
                barY.add(i * 270);
            }
        }

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

      //  gameOverIntent = new Intent(getContext(), GameOverActivity.class);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = getWidth();
        canvasHeight = getHeight();

        //background
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        //Ball rules
        int maxBallY = canvasHeight - (Ball[0].getHeight() / 2);
        int maxBallX = canvasWidth - Ball[0].getWidth();
        int maxEk = canvasHeight -(Ball[0].getHeight()*2);
        int minBallX = 0;
        BallY += BallSpeed;

        if(BallY > maxBallY) {
            BallY = maxBallY;
            isFelt = true;
        }
        if(BallX > maxBallX)
            BallX = maxBallX;

        if(BallX < minBallX)
            BallX = minBallX;

        BallSpeed += ballSpeedAmplify;
        if(leftMove)
            BallX -= 25;
        if(rightMove)
            BallX += 25;
        //if(score % 10 == 0 && score != 0 && amp) {
         //   ballSpeedAmplify += 1;
          //  amp = false;
      //  }

        //draw Ball
        if(direction)
            canvas.drawBitmap(Ball[1], BallX, BallY, null);
        else
            canvas.drawBitmap(Ball[0], BallX, BallY, null);

        //draw Bar
        for(int i = 0; i < barAmount; i++){
            if(barY.get(i) > canvasHeight + 10) {
                barY.set(i, 0);
                barX.set(i, (int) Math.floor(Math.random() * (canvasWidth - bar[0].getWidth())));
            }
            canvas.drawBitmap(bar[i], barX.get(i), barY.get(i), null);
        }


        //check
        for(int i = 0; i < barAmount; i++) {
            if(hitBallChecker(barX.get(i), barY.get(i), bar[i])){
                BallSpeed = -20;
                score += 1;
                amp = true;
                for(int j = 0; j < barAmount; j++){
                    barY.set(j, barY.get(j) + 153);
                }
            }
        }

        //Game Over Case
        if(isFelt){
            //Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();


           // gameOverIntent = new Intent(getContext(), GameOverActivity.class);
            Intent gameOverIntent = new Intent(getContext(),GameOverActivity.class);


            gameOverIntent.putExtra("score", score);
            gameOverIntent.addFlags(  Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK   );
            getContext().startActivity(gameOverIntent);






    }
        //Score bar
        canvas.drawText("Score: " + score, 40, 60, scorePaint);

    }
    //
    public boolean hitBallChecker(int x, int y, Bitmap b){
        return BallX + (Ball[0].getWidth() / 2) > (x - (Ball[0].getWidth() / 4))
                && BallX + (Ball[0].getWidth() / 2) < x + b.getWidth() + (Ball[0].getWidth() / 4)
                && BallY + Ball[0].getHeight() > y && BallY + Ball[0].getHeight() < (y + b.getHeight() / 2);
    }


    //Action listener
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sX, sY;
        sX = event.getX();
        sY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(sX >= 0 && sX <= (float)(canvasWidth / 2)) {
                    direction = true;
                    leftMove = true;
                }
                else if(sX >= (float)(canvasWidth / 2) && sX <= (float)(canvasWidth)) {
                    direction = false;
                    rightMove = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                leftMove = rightMove = false;
                break;
        }
        return true;
    }
}