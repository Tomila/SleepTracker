package com.example.sleeptrackeralpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextClock;

/**
 * Sisältää metodit ajastimen käynnistykseen, sammuttamiseen ja
 * SleepData aktiviteettiin siirtymiseen
 * @author Arttu Iso-Kuortti
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {

    public Chronometer ajastin;
    public TextClock juttu;
    private long pauseOffset;
    private boolean kaynnissa;
    SharedPreferences sharedPreferences;

    /**
     * Asettaa arvot xml.tiedoston kohteille
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ajastin = findViewById(R.id.kello);
        juttu = this.findViewById(R.id.juttu);
        ajastin.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){

            public void onChronometerTick(Chronometer chronometer) {

                //Asettaa chronometerin näyttämään tunnit
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                int h   = (int)(time /3600000);
                int m = (int)(time - h*3600000)/60000;
                int s= (int)(time - h*3600000- m*60000)/1000 ;
                String tunnit = (h < 10 ? "0"+h: h)+":"+(m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
                chronometer.setText(tunnit);
            }
        });
        ajastin.setBase(SystemClock.elapsedRealtime());
        ajastin.setText("00:00:00");
    }

    /**
     * Start-näppäintä painettaessa käynnistää ajastimen
     * @param v
     */
    public void startti(View v) {

        //Jos ajastin on jo käynnissä "startti" ei tee mitään
        if (!kaynnissa) {

            //Käynnistää ajastimen
            ajastin.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            ajastin.start();
            kaynnissa = true;
        }
    }

    /**
     * Stop-näppäintä painettaessa sammuttaa ajastimen, tallentaa arvot SharedPreferenssiin
     * ja käynnistää Ratingbar aktiviteetin
     * @param v
     */
    public void resetti(View v) {

        //Asettaa kuluneen ajan ja päivämäärän SharedPreferenssiin
        sharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("aika", "Time slept:  " + ajastin.getText().toString());
        editor.putString("paiva" ,"Date:  " + juttu.getText().toString());
        editor.apply();

        //Sammuttaa ajastimen
        ajastin.setBase(SystemClock.elapsedRealtime());
        ajastin.stop();
        pauseOffset = 0;
        kaynnissa = false;

        //Käynnistää Ratingbar aktiviteetin
        Intent unenlaatu = new Intent(getBaseContext(), Ratingbar.class);
        startActivity(unenlaatu);
    }

    /**
     * Sleepdata-näppäintä painettaessa käynnistää SleepData aktiviteetin
     * @param v
     */
    public void historia(View v){

        //Käynnistää SleepData aktiviteetin
        Intent historia = new Intent(getBaseContext(), SleepData.class);
        startActivity(historia);
    }
}





