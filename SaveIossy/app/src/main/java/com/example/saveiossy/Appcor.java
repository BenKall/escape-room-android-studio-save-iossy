package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Appcor extends AppCompatActivity {

    private Button btnAppcorToNameInput, btnAppcorToServers, btnAppcorToLibrary, btnAppcorToAppclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appcor);

        getSupportActionBar().setTitle("Application corridor");

        btnAppcorToNameInput = findViewById(R.id.btn_appcor_to_name_input);
        btnAppcorToAppclass = findViewById(R.id.btn_appcor_to_appclass);
        btnAppcorToServers = findViewById(R.id.btn_appcor_to_servers);
        btnAppcorToLibrary = findViewById(R.id.btn_appcor_to_library);

        btnAppcorToNameInput.setOnClickListener(v -> {
            Intent it = new Intent(Appcor.this,NameInput.class);
            startActivity(it);
        });

        btnAppcorToAppclass.setOnClickListener(v -> {
            Intent it = new Intent(Appcor.this,Appclass.class);
            startActivity(it);
        });

        btnAppcorToLibrary.setOnClickListener(v -> {
            Intent it = new Intent(Appcor.this,Library.class);
            startActivity(it);
        });

        btnAppcorToServers.setOnClickListener( v -> {
            Intent it = new Intent(Appcor.this,SharatClass.class);
            startActivity(it);
        });
    }
}