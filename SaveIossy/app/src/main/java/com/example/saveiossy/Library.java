package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Library extends AppCompatActivity {

    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    private Button btnLockToLibrary, btnLibraryToAppcor, btnLibraryToInLibrary;
    private Boolean[] cbState = {false, false, false, false, false, false};
    private CircleImageView circleImageView;
    private RadioGroup rg,rgcircle;
    private RelativeLayout layout = null;
    private RadioButton rbCow,rbCat,rbMilk,rbMan;
    private Switch sw;
    private TextView tvSecretCode;
    private boolean isDoorOpen = false;
    private int combinationCount = 0 ;
    private MediaPlayer mSound = null;

    ////////

    public void disableCb() // disables Checkboxes
    {
        CheckBox[] cbList = {cb1, cb2, cb3, cb4, cb5};

        for (CheckBox box : cbList)
            box.setEnabled(false);

        mSound = MediaPlayer.create(Library.this,Const.soundTrack.get("redgreen button"));
        mSound.start();
    }

    public void enableCb() //enables Checkboxes
    {
        new Handler().postDelayed(() -> {
            CheckBox[] cbList = {cb1, cb2, cb3, cb4, cb5};

                for (CheckBox box : cbList)
                    box.setEnabled(true);

        }, 500);
    }


    public void checkFinalSolution() //Checks if all 6 'cb' are checked
    {
        if(Const.libraryKeyA&&cb6.isChecked()) {
            Toast.makeText(this, "you unlocked the door!", Toast.LENGTH_SHORT).show();
            mSound = MediaPlayer.create(Library.this,Const.soundTrack.get("hint"));
            mSound.start();
            Const.libraryKeyAB = true;
        }
    }

    public void checkSolution( ) //Checks if first 5 'cb' are checked
    {
        if(cb1.isChecked()==true&&cb2.isChecked()==true&&cb3.isChecked()==true&&cb4.isChecked()==true&&cb5.isChecked()==true) {
            Const.libraryKeyA = true;
            CheckBox[] cbList = {cb1, cb2, cb3, cb4, cb5};
            for (CheckBox box : cbList)
                box.setEnabled(false);
            checkFinalSolution();

        }
    }

    public void disableRadioGroup()
    {
        rgcircle.setEnabled(false);
    }

    public void rgSound()
    {
        mSound = MediaPlayer.create(Library.this,Const.soundTrack.get("beep"));
        mSound.start();
    }

    public void combinationProgress() // Checks if player is following the combination, when it's complete, part of the puzzle is solved
    {
        switch (combinationCount)
        {
            case 0:
                rgSound();
                if(rbCow.isChecked())
                    combinationCount++;
                break;
            case 1:
                rgSound();
                if(rbCat.isChecked())
                    combinationCount++;
                else
                    combinationCount = 0;
                break;
            case 2:
                rgSound();
                if(rbCow.isChecked())
                    combinationCount++;
                else
                    combinationCount = 0;
                break;
            case 3:
                rgSound();
                if(rbMilk.isChecked())
                    combinationCount++;
                else
                    combinationCount = 0;
                break;
            case 4:
                rgSound();
                if(rbMan.isChecked())
                    combinationCount++;
                else
                    combinationCount = 0;
                break;
            case 5:
                if(rbCat.isChecked()==true) {
                    cb6.setEnabled(true);
                    cb6.setBackgroundResource(R.drawable.redlight);
                    mSound = MediaPlayer.create(Library.this,Const.soundTrack.get("light solved"));
                    mSound.start();
                    disableRadioGroup();
                    checkFinalSolution();
                }
                else
                    combinationCount = 0;
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        getSupportActionBar().setTitle("Outside Library");

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        cb6 = findViewById(R.id.cb6);
        sw = findViewById(R.id.sw);
        rg = findViewById(R.id.rg);
        layout = findViewById(R.id.library);
        tvSecretCode = findViewById(R.id.tv_secret_code);
        btnLockToLibrary = findViewById(R.id.btn_lock_to_library);
        btnLibraryToAppcor = findViewById(R.id.btn_library_to_appcor);
        btnLibraryToInLibrary = findViewById(R.id.btn_library_to_in_library);

        rbCow = findViewById(R.id.rb_cow);
        rbCat = findViewById(R.id.rb_cat);
        rbMilk = findViewById(R.id.rb_milk);
        rbMan = findViewById(R.id.rb_man);

        rgcircle = findViewById(R.id.rgcircle);
        circleImageView = findViewById(R.id.cricImage);

        btnLibraryToAppcor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDoorOpen) {
                    layout.setBackgroundResource(R.drawable.libarrarydoor2);
                    isDoorOpen = false;
                    View[] views = {rg, circleImageView, rgcircle, btnLockToLibrary, sw};
                    for (View vLock : views)
                        vLock.setVisibility(View.VISIBLE);
                    btnLibraryToInLibrary.setVisibility(View.INVISIBLE);
                }
                else {
                    Intent it = new Intent(Library.this, Appcor.class);
                    startActivity(it);
                }
            }
        });

        btnLibraryToInLibrary.setOnClickListener(v -> {
                Intent it = new Intent(Library.this, InLibrary.class);
                startActivity(it);
        });


        btnLockToLibrary.setOnClickListener(v -> {
            if(Const.libraryKeyAB){
                mSound = MediaPlayer.create(Library.this, Const.soundTrack.get("unlock"));
                layout.setBackgroundResource(R.drawable.libarrarydooropen);
                //layout.setBackground(getResources().getDrawable(R.drawable.libarrarydooropen)); Does the same thing
                View[] views = {rg, circleImageView, rgcircle, btnLockToLibrary, sw};
                for (View vUnlock : views)
                    vUnlock.setVisibility(View.INVISIBLE);
                isDoorOpen = true;
                btnLibraryToInLibrary.setVisibility(View.VISIBLE);
            }
            else
                mSound = MediaPlayer.create(Library.this, Const.soundTrack.get("locked"));
            mSound.start();
        });

        cb1.setOnClickListener(v -> {
            disableCb();
            if(cbState[0]){
                cb1.setBackgroundResource(R.drawable.redlight);
                cbState[0] = false;
            }
            else
            {
                cb1.setBackgroundResource(R.drawable.greenlight);
                cbState[0] = true;
            }
            if(cb3.isChecked()) {
                cb3.setBackgroundResource(R.drawable.redlight);
                cb3.setChecked(false);
            }
            else {
                cb3.setBackgroundResource(R.drawable.greenlight);
                cb3.setChecked(true);
            }
            enableCb();
            checkSolution();
        });
        cb2.setOnClickListener(v -> {
            disableCb();
            if(cbState[1]){
                cb2.setBackgroundResource(R.drawable.redlight);
                cbState[1] = false;
            }
            else
            {
                cb2.setBackgroundResource(R.drawable.greenlight);
                cbState[1] = true;
            }
            if(cb3.isChecked()) {
                cb3.setBackgroundResource(R.drawable.redlight);
                cb3.setChecked(false);
            }
            else {
                cb3.setBackgroundResource(R.drawable.greenlight);
                cb3.setChecked(true);
            }
            if(cb4.isChecked()) {
                cb4.setBackgroundResource(R.drawable.redlight);
                cb4.setChecked(false);
            }
            else {
                cb4.setBackgroundResource(R.drawable.greenlight);
                cb4.setChecked(true);
            }
            checkSolution();
            enableCb();
        });
        cb3.setOnClickListener(v -> {
            disableCb();
            if(cbState[2]){
                cb3.setBackgroundResource(R.drawable.redlight);
                cbState[2] = false;
            }
            else
            {
                cb3.setBackgroundResource(R.drawable.greenlight);
                cbState[2] = true;
            }
            if(cb4.isChecked()){
                cb4.setBackgroundResource(R.drawable.redlight);
                cb4.setChecked(false);
            }
            else
            {
                cb4.setBackgroundResource(R.drawable.greenlight);
                cb4.setChecked(true);
            }
            checkSolution();
            enableCb();
        });
        cb4.setOnClickListener(v -> {
            disableCb();
            if(cbState[3]){
                cb4.setBackgroundResource(R.drawable.redlight);
                cbState[3] = false;
            }
            else
            {
                cb4.setBackgroundResource(R.drawable.greenlight);
                cbState[3] = true;
            }
            if(cb1.isChecked()){
                cb1.setBackgroundResource(R.drawable.redlight);
                cb1.setChecked(false);
            }
            else
            {
                cb1.setBackgroundResource(R.drawable.greenlight);
                cb1.setChecked(true);
            }
            if(cb5.isChecked()){
                cb5.setBackgroundResource(R.drawable.redlight);
                cb5.setChecked(false);
            }
            else
            {
                cb5.setBackgroundResource(R.drawable.greenlight);
                cb5.setChecked(true);
            }
            checkSolution();
            enableCb();
        });
        cb5.setOnClickListener(v -> {
            disableCb();
            if(cbState[4]){
                cb5.setBackgroundResource(R.drawable.redlight);
                cbState[4] = false;
            }
            else
            {
                cb5.setBackgroundResource(R.drawable.greenlight);
                cbState[4] = true;
            }
            if(cb1.isChecked()){
                cb1.setBackgroundResource(R.drawable.redlight);
                cb1.setChecked(false);
            }
            else
            {
                cb1.setBackgroundResource(R.drawable.greenlight);
                cb1.setChecked(true);
            }
            checkSolution();
            enableCb();
        });
        cb6.setOnClickListener(v -> {
            disableCb();
            if(cbState[5]){
                cb6.setBackgroundResource(R.drawable.redlight);
                cbState[5] = false;
            }
            else
            {
                cb6.setBackgroundResource(R.drawable.greenlight);
                cbState[5] = true;
            }
            checkSolution();
            enableCb();
        });

        rgcircle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.rb_cow:
                        circleImageView.setImageResource(R.drawable.cow);
                        break;
                    case R.id.rb_cat:
                        circleImageView.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rb_milk:
                        circleImageView.setImageResource(R.drawable.milk);
                        break;
                    case R.id.rb_man:
                        circleImageView.setImageResource(R.drawable.man);
                        break;
                }
            }
        });
        rbCow.setOnClickListener(view -> combinationProgress());
        rbCat.setOnClickListener(view -> combinationProgress());
        rbMilk.setOnClickListener(view -> combinationProgress());
        rbMan.setOnClickListener(view -> combinationProgress());

        sw.setOnClickListener(view -> {
            if(sw.isChecked()==true) {
                mSound = MediaPlayer.create(Library.this,Const.soundTrack.get("light switch"));
                mSound.start();
                layout.setBackground(getResources().getDrawable(R.drawable.libarrarydoor2));
                View[] views = {rg, circleImageView, rgcircle, btnLockToLibrary, tvSecretCode};
                for (View v : views) {
                    if(v instanceof TextView && !(v instanceof Button))
                        v.setVisibility(View.INVISIBLE);
                    else
                        v.setVisibility(View.VISIBLE);
                }
                combinationCount = 0;
            }
            else{
                mSound = MediaPlayer.create(Library.this,Const.soundTrack.get("light switch"));
                mSound.start();
                layout.setBackgroundColor(Color.BLACK);
                View[] views = {rg, circleImageView, rgcircle, btnLockToLibrary, tvSecretCode};
                for (View v : views) {
                    if(v instanceof TextView && !(v instanceof Button))
                        v.setVisibility(View.VISIBLE);
                    else
                        v.setVisibility(View.INVISIBLE);
                }
                combinationCount =0;
            }
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