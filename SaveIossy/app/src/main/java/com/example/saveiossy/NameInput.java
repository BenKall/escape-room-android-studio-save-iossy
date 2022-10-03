package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NameInput extends AppCompatActivity {

    private Button btnNameInputToMain, btnNameInputToAppcor;
    private EditText etPlayerName;
    private TextView playerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_input);

        getSupportActionBar().setTitle("Name Input");

        btnNameInputToAppcor = findViewById(R.id.btn_name_input_to_appcor);
        btnNameInputToMain = findViewById(R.id.btn_name_input_to_main);
        etPlayerName = findViewById(R.id.et_player_name);
        playerName = findViewById(R.id.player_name);

        if(Const.hasName) { // Checks if player had input a name in the past

            playerName.setText("You are: " + Const.shownPlayerName);
            etPlayerName.setHint(Const.shownPlayerName);
        }

        btnNameInputToMain.setOnClickListener(v -> {
            Intent it = new Intent(NameInput.this,MainActivity.class);
            startActivity(it);
        });

        btnNameInputToAppcor.setOnClickListener(v -> {
            if(!etPlayerName.getText().toString().equals("") || Const.hasName) { // Checks if player hasn't input a name or if it had done it before in the past
                if(!etPlayerName.getHint().toString().equals("") && Const.hasName)
                    Const.playerName = etPlayerName.getHint().toString(); // Updates a player's name
                    Const.setShownPlayerName(etPlayerName.getHint().toString());
                if(!Const.hasName) {
                    Const.playerName = etPlayerName.getText().toString(); // Gives player the name that they input
                    Const.setShownPlayerName(etPlayerName.getText().toString());
                    Const.hasName = true; // sets hasName flag true, letting the program know that the player has input their name for the first time
                }
                Toast.makeText(NameInput.this, "Your name has been saved", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(NameInput.this, Appcor.class);
                startActivity(it);
            }
            else
            Toast.makeText(NameInput.this, "Please input a name", Toast.LENGTH_SHORT).show();
        });
    }
}