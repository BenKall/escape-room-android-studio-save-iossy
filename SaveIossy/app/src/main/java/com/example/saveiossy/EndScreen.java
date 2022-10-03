package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EndScreen extends AppCompatActivity {

    private MediaPlayer mSound = null;
    private Button btnEndScreenToMain;
    private TextView tvDisPlayerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        getSupportActionBar().setTitle("");

        Const.finishedGame = true;

        btnEndScreenToMain = findViewById(R.id.btn_end_screen_to_main);
        tvDisPlayerName = findViewById(R.id.tv_dis_player_name);

        tvDisPlayerName.setText(Const.shownPlayerName);

        mSound = MediaPlayer.create(EndScreen.this,Const.soundTrack.get("end music"));
        //mSound.setLooping(true);
        mSound.start();

        btnEndScreenToMain.setOnClickListener(v -> {
            mSound.release();
            Intent it = new Intent(EndScreen.this,MainActivity.class);
            startActivity(it);
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(mSound != null)
        {
            if(mSound.isPlaying())
                mSound.stop();

            mSound.release();

            mSound =null;
        }
    }
}