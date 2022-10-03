package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnCredits, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        btnCredits = findViewById(R.id.btn_credits);
        btnReset = findViewById(R.id.btn_reset);

        if(Const.finishedGame)
            btnReset.setVisibility(View.VISIBLE);

        btnReset.setOnClickListener(v -> {
            Const.resetGame();
            if(!Const.finishedGame)
                btnReset.setVisibility(View.INVISIBLE);
        });

        btnStart.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this,NameInput.class);
            startActivity(it);
        });

        btnCredits.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this,Credits.class);
            startActivity(it);
        });

    }
}