package com.example.sleeptrackeralpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Näyttää käyttäjälle viimeisimmän nukutun yön tiedot
 * @author Aku Korhonen
 * @version 1.0
 */

public class SleepData extends AppCompatActivity {

    private TextView arvo, arvo2, arvo3;

    /**
     * Asettaa arvot xml.tiedoston kohteille,
     * hakee arvot SharedPreferenssistä,
     * asettaa päivämäärän, kuluneen ajan ja käyttäjän arvion tekstikenttiin
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_data);

        //Asettaa arvot xml.tiedoston kohteille
        arvo = this.findViewById(R.id.lopputulos);
        arvo2 = this.findViewById(R.id.lopputulos2);
        arvo3 = this.findViewById(R.id.lopputulos3);

        //Hakee arvot SharedPreferenssistä
        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String value = result.getString("aika", "value not found");
        String value2 = result.getString("paiva", "value not found");
        String value3 = result.getString("arvio", "value not found");

        //Asettaa arvot tekstikenttiin
        arvo.setText(value);
        arvo2.setText(value2);
        arvo3.setText(value3);
    }
}
