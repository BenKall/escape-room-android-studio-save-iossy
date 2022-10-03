package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class GlowingScreen extends AppCompatActivity {

    private Button btnGlowingScreenToAppclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glowing_screen);

        getSupportActionBar().setTitle("Screen");

        btnGlowingScreenToAppclass = findViewById(R.id.btn_glowing_scrren_to_appclass);

        btnGlowingScreenToAppclass.setOnClickListener(v -> {
            Intent it = new Intent(GlowingScreen.this,Appclass.class);
            startActivity(it);
        });
    }
}