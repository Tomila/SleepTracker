package com.example.sleeptrackeralpha;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Kysyy käyttäjältä arviota unenlaadustaan ja tallettaa sen sharedpreferenssiin
 * @author Tomi Laine
 * @version 1.0
 */

public class Ratingbar extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;
    SharedPreferences sharedPreferences;

    /**
     * Kutsuu metodeja addListenerOnRatingBar(), addListenerOnButton() ja
     * asettaa arvot xml.tiedoston kohteille
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingbar);
        ratingBar = findViewById(R.id.ratingBar);
        txtRatingValue = findViewById(R.id.txtRatingValue);
        btnSubmit = findViewById(R.id.btnSubmit);

        addListenerOnRatingBar();
        addListenerOnButton();
    }

    /**
     * Asettaa kuuntelijan ratingbariin,
     * kun ratingbarin arvo muuttuu uusi arvo näkyy heti tekstikentässä
     */
    public void addListenerOnRatingBar() {

        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    /**
     * Asettaa kuuntelijan submit-painikkeeseen,
     * Submit nappia painettaessa ratingbarin arvo tallentuu sharedpreferenssin
     * ja näkyy myös ponnahdusikkunassa käyttäjälle
     */
    public void addListenerOnButton() {

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                //Näyttää käyttäjän arvion ponnahdusikkunassa
                Toast.makeText(Ratingbar.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();

                //Asettaa käyttäjän arvion SharedPreferenssiin
                sharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("arvio" ,"Rating:  " + ratingBar.getRating());
                editor.apply();

                //Käynnistää MainActivityn
                startActivity( new Intent(Ratingbar.this, MainActivity.class));
            }

        });

    }
}



