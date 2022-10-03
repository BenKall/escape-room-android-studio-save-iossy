package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InLibrary extends AppCompatActivity {

    private Button btnInLibraryToLibrary, btnInLibraryToComplib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_library);

        getSupportActionBar().setTitle("Inside Library");

        btnInLibraryToComplib = findViewById(R.id.btn_in_library_to_complib);
        btnInLibraryToLibrary = findViewById(R.id.btn_in_library_to_library);

        btnInLibraryToLibrary.setOnClickListener(v -> {
            Intent it = new Intent(InLibrary.this,Library.class);
            startActivity(it);
        });

        btnInLibraryToComplib.setOnClickListener(v -> {
            Intent it = new Intent(InLibrary.this,Complib.class);
            startActivity(it);
        });

    }
}