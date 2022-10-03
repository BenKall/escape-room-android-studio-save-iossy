package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Credits extends AppCompatActivity {
    private Button btnFinalClue, btnCreditsToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        getSupportActionBar().setTitle("Credits");

        btnFinalClue = findViewById(R.id.btn_final_clue);
        btnCreditsToMain = findViewById(R.id.btn_credits_to_main);

        btnFinalClue.setOnClickListener(v -> {
            // Displays password for true end
            Toast.makeText(Credits.this, "173451", Toast.LENGTH_SHORT).show();
        });

        btnCreditsToMain.setOnClickListener(v -> {
            Intent it = new Intent(Credits.this,MainActivity.class);
            startActivity(it);
        });
    }
}