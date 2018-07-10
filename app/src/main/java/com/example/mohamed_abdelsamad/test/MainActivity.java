package com.example.mohamed_abdelsamad.test;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  implements SensorEventListener{


      boolean dark=false,light=false;
    Sensor sensor;
    SensorManager sensorManager;
    MediaPlayer md,mp ;
    Button btn;
    public int colore,textcol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        btn=(Button) findViewById(R.id.btn);
        md = (MediaPlayer.create(this, R.raw.alert));



    }


            @Override
            protected void onPause() {
                super.onPause();
                sensorManager.unregisterListener( this);
            }

            @Override
            protected void onResume() {
                super.onResume();
                sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

            }

            @Override
            public void onSensorChanged(SensorEvent event) {
              if (event.values[0] < 1 && !dark) {
                   if(md.isPlaying()){
                       md.pause();
                   }
                            dark=true;
                           light=false;

                  md.start();


                           btn.setText("the light is off");
                           textcol = btn.getResources().getColor(R.color.colorAccent);
                           btn.setTextColor(textcol);
                       }

                    else
                     if (event.values[0] >= 10  && !light) {
                         if(md.isPlaying()){
                             md.pause();
                         }
                                          light=true;
                                          dark=false;
                                          md.start();
                                          btn.setText("the light is on");
                                          textcol = btn.getResources().getColor(R.color.colordark);
                                          btn.setTextColor(textcol);
                      }
                    }






    @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }

