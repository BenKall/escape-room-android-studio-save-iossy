package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Complib extends AppCompatActivity {

    private Button btnComplibToComplibScreen, btnComplibToInLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complib);

        getSupportActionBar().setTitle("Library Computers");

        btnComplibToComplibScreen = findViewById(R.id.btn_complib_to_complib_screen);
        btnComplibToInLibrary = findViewById(R.id.btn_complib_to_in_library);

        btnComplibToComplibScreen.setOnClickListener(v -> {
            Intent it = new Intent(Complib.this,ComplibScreen.class);
            startActivity(it);
        });

        btnComplibToInLibrary.setOnClickListener(v -> {
            Intent it = new Intent(Complib.this,InLibrary.class);
            startActivity(it);
        });
    }
}