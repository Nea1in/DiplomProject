package com.anthony.marco.balljumpgame;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(mainIntent);finish();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });

        Button button1=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast toast3 = Toast.makeText(getApplicationContext(),
                        "                                                                       ", Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastContainer = (LinearLayout) toast3.getView();
                ImageView catImageView = new ImageView(getApplicationContext());
                catImageView.setImageResource(R.drawable.help);
                toastContainer.addView(catImageView, 0);

                toast3.show();





            }
        });


Button button2=(Button)findViewById(R.id.button3);
button2.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   finishAffinity();
                               }
                           });
       /*Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }
            }
        };*/


    }


    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}
