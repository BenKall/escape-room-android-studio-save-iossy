package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Appclass extends AppCompatActivity {
    private Button btnUp1, btnLetter1, btnDown1, btnUp2, btnLetter2, btnDown2, btnUp3, btnLetter3, btnDown3, btnUp4, btnLetter4, btnDown4, btnLockToAppclass, btnAppclassToAppcor;
    private String abc ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private char[] abcChar = abc.toCharArray();
    private ImageView ivPassLock;
    private MediaPlayer mSound = null;
    ////////////////////
    public void checkfinalsol() //Checks if password is correct
    {
        if( btnLetter1.getText().toString().equals("Y") && btnLetter2.getText().toString().equals("O") && btnLetter3.getText().toString().equals("N") && btnLetter4.getText().toString().equals("A") )
        {
            Const.fourDigitPass = true;
            disableFourDigitPassBtn();
        }
    }

    public void upBtnAction(Button btnLetter) // Changes the button's text to the next letter in the ABC
    {
        String s1,s2;
        for (int i = 0; i < abc.length()-1; i++)
        {
            s1=String.valueOf(abcChar[i]);
            s2=String.valueOf(abcChar[i+1]);
            if(btnLetter.getText().toString().equals("Z")){
                btnLetter.setText("A");
                checkfinalsol();
                return;
            }
            if(btnLetter.getText().toString().equals(s1))
            {
                btnLetter.setText(s2);
                checkfinalsol();
                return;
            }
        }
    }

    public void downBtnAction(Button btnLetter) // Changes the button's text to the previous letter in the ABC
    {
        String s1,s2;
        for (int i = 1; i < abc.length(); i++)
        {
            s1=String.valueOf(abcChar[i]);
            s2=String.valueOf(abcChar[i-1]);
            if(btnLetter.getText().toString().equals("A")){
                btnLetter.setText("Z");
                checkfinalsol();
                return;
            }
            if(btnLetter.getText().toString().equals(s1))
            {
                btnLetter.setText(s2);
                checkfinalsol();
                return;
            }
        }
    }

    public void disableFourDigitPassBtn() // makes four digit pass code buttons unclickable
    {
        Button[] btnList = {btnUp1, btnUp2, btnUp3, btnUp4, btnDown1, btnDown2, btnDown3, btnDown4};
        for (int i = 0; i < btnList.length; i++)
            btnList[i].setEnabled(false);
        mSound = MediaPlayer.create(Appclass.this,Const.soundTrack.get("light solved"));
        mSound.start();
        ivPassLock.setImageResource(R.drawable.greenlight);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appclass);

        getSupportActionBar().setTitle("Application Classroom");

        btnUp1 = findViewById(R.id.btn_up1);
        btnLetter1 = findViewById(R.id.btn_letter1);
        btnDown1 = findViewById(R.id.btn_down1);
        btnUp2 = findViewById(R.id.btn_up2);
        btnLetter2 = findViewById(R.id.btn_letter2);
        btnDown2 = findViewById(R.id.btn_down2);
        btnUp3 = findViewById(R.id.btn_up3);
        btnLetter3 = findViewById(R.id.btn_letter3);
        btnDown3 = findViewById(R.id.btn_down3);
        btnUp4 = findViewById(R.id.btn_up4);
        btnLetter4 = findViewById(R.id.btn_letter4);
        btnDown4 = findViewById(R.id.btn_down4);
        ivPassLock = findViewById(R.id.iv_pass_lock);

        btnAppclassToAppcor = findViewById(R.id.btn_appclass_to_appcor);
        btnLockToAppclass = findViewById(R.id.btn_lock_to_appclass);

        if(Const.fourDigitPass)
        {
            btnLetter1.setText("Y");
            btnLetter2.setText("O");
            btnLetter3.setText("N");
            btnLetter4.setText("A");
            disableFourDigitPassBtn();
        }

        btnAppclassToAppcor.setOnClickListener(v -> {
            Intent it = new Intent(Appclass.this,Appcor.class);
            startActivity(it);
        });

        btnLockToAppclass.setOnClickListener(v -> {
            if(Const.approomKey && Const.fourDigitPass)
            {
                mSound = MediaPlayer.create(Appclass.this,Const.soundTrack.get("unlock"));
                mSound.start();
                Intent it = new Intent(Appclass.this,InAppclass.class);
                startActivity(it);
            }
            else
            {
                mSound = MediaPlayer.create(Appclass.this,Const.soundTrack.get("locked"));
                mSound.start();
            }
        });

        btnUp1.setOnClickListener(v -> upBtnAction(btnLetter1));

        btnDown1.setOnClickListener(v -> downBtnAction(btnLetter1));

        btnUp2.setOnClickListener(v -> upBtnAction(btnLetter2));

        btnDown2.setOnClickListener(v -> downBtnAction(btnLetter2));

        btnUp3.setOnClickListener(v -> upBtnAction(btnLetter3));

        btnDown3.setOnClickListener(v -> downBtnAction(btnLetter3));

        btnUp4.setOnClickListener(v -> upBtnAction(btnLetter4));

        btnDown4.setOnClickListener(v -> downBtnAction(btnLetter4));

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