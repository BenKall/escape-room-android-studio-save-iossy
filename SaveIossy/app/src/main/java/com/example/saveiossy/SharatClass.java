package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class SharatClass extends AppCompatActivity {

    private RatingBar rbSharatClass;
    private Button btnTime , btnLockToOpenDoor, btnSharatclassToInSharatclass, btnSharatclassToAppcor;
    private SeekBar sbSharatClass;
    private int hr, min, mHr , mMin;
    private MediaPlayer mSound = null;
    private Switch sw1, sw2, sw3, sw4, sw5, sw6, sw7, sw8;
    private Spinner spnTeachers;
    private ArrayList<String> arrayList = new ArrayList<>();
    private boolean isIossy = false, isDoorOpen = false;
    private RelativeLayout layout;
    private boolean hasActivityJustOpened = true;


    public void checksolution()
    {
        if(sw1.isChecked()==false && sw2.isChecked()==true && sw3.isChecked()==false && sw4.isChecked()==false && sw5.isChecked()==true && sw6.isChecked()==true && sw7.isChecked()==false && sw8.isChecked()==false && mHr==18 && mMin==40 && rbSharatClass.getRating()==5 && sbSharatClass.getProgress()==100 && isIossy )
        {
            mSound = MediaPlayer.create(SharatClass.this,Const.soundTrack.get("hint"));
            mSound.start();
            Const.sharatKey=true;
            View[] views = {rbSharatClass, sbSharatClass, btnTime, spnTeachers, sw1, sw2, sw3 ,sw4 ,sw5, sw6, sw7, sw8};
            for (View v : views) {
                v.setEnabled(false);
            }
            Toast.makeText(this, "you unlocked the door!", Toast.LENGTH_SHORT).show();
        }
    }

    public void switchSound()
    {
            mSound = MediaPlayer.create(SharatClass.this, Const.soundTrack.get("light switch"));
            mSound.start();
    }

    public void spnSound()
    {
            mSound = MediaPlayer.create(SharatClass.this, Const.soundTrack.get("mediumpress"));
            mSound.start();
    }

    public void openDoor()
    {
        layout.setBackground(getResources().getDrawable(R.drawable.sharatdooropen));
        View[] views = {rbSharatClass, btnLockToOpenDoor, sbSharatClass, btnTime, spnTeachers, sw1, sw2, sw3 ,sw4 ,sw5, sw6, sw7, sw8};
        for (View v : views) {
            v.setVisibility(View.INVISIBLE);
        }
        isDoorOpen = true;
        btnSharatclassToInSharatclass.setVisibility(View.VISIBLE);
    }

    public void closeDoor()
    {
        layout.setBackground(getResources().getDrawable(R.drawable.sharatdoor));
        View[] views = {rbSharatClass, btnLockToOpenDoor, sbSharatClass, btnTime, spnTeachers, sw1, sw2, sw3 ,sw4 ,sw5, sw6, sw7, sw8};
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
        isDoorOpen = false;
        btnSharatclassToInSharatclass.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharat_class);

        getSupportActionBar().setTitle("Sharat Classroom");

        rbSharatClass = findViewById(R.id.rb_sharat_class);
        btnTime = findViewById(R.id.btn_time);
        btnLockToOpenDoor = findViewById(R.id.btn_lock_to_open_door);
        sbSharatClass = findViewById(R.id.sb_sharat_class);
        sw1 = findViewById(R.id.sw1);
        sw2 = findViewById(R.id.sw2);
        sw3 = findViewById(R.id.sw3);
        sw4 = findViewById(R.id.sw4);
        sw5 = findViewById(R.id.sw5);
        sw6 = findViewById(R.id.sw6);
        sw7 = findViewById(R.id.sw7);
        sw8 = findViewById(R.id.sw8);
        layout = findViewById(R.id.sharat_class);
        spnTeachers = findViewById(R.id.spn_teachers);
        btnSharatclassToInSharatclass = findViewById(R.id.btn_sharatclass_to_in_sharatclass);
        btnSharatclassToAppcor = findViewById(R.id.btn_sharatclass_to_appcor);


        arrayList.add("Talia");
        arrayList.add("Ravit");
        arrayList.add("Motti");
        arrayList.add("Iossy");
        arrayList.add("Yona");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SharatClass.this, R.layout.spn_design, arrayList);
        spnTeachers.setAdapter(adapter);
        spnTeachers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position)
                {
                    case 0:
                        isIossy = false;
                        if(hasActivityJustOpened)
                            hasActivityJustOpened = false;
                        else
                            spnSound();
                        checksolution();
                        break;
                    case 1:
                        isIossy = false;
                        spnSound();
                        checksolution();
                        break;
                    case 2:
                        isIossy = false;
                        spnSound();
                        checksolution();
                        break;
                    case 3:
                        isIossy = true;
                        spnSound();
                        checksolution();
                        break;
                    case 4:
                        isIossy = false;
                        spnSound();
                        checksolution();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rbSharatClass.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            mSound = MediaPlayer.create(SharatClass.this, Const.soundTrack.get("platepress"));
            mSound.start();
            checksolution();
        });

        btnTime.setOnClickListener(v -> {
            mSound = MediaPlayer.create(SharatClass.this, Const.soundTrack.get("metalpress"));
            mSound.start();

            TimePickerDialog timePickerDialog = new TimePickerDialog(SharatClass.this, (timePicker, hr, min) -> {
                mHr=hr;
                mMin=min;
                checksolution();
            },hr,min,true);
            timePickerDialog.show();
        });

        sbSharatClass.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mSound = MediaPlayer.create(SharatClass.this, Const.soundTrack.get("platepress"));
                mSound.start();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mSound = MediaPlayer.create(SharatClass.this, Const.soundTrack.get("mediumpress"));
                mSound.start();
                checksolution();
            }
        });

        btnSharatclassToInSharatclass.setOnClickListener(v -> {
            Intent it = new Intent(SharatClass.this,InSharatclass.class);
            startActivity(it);
        });

        btnSharatclassToAppcor.setOnClickListener(v -> {
            if(isDoorOpen)
            {
                closeDoor();
            }
            else{
                Intent it = new Intent(SharatClass.this,Appcor.class);
                startActivity(it);
            }

        });

        btnLockToOpenDoor.setOnClickListener(v -> {
            if(Const.sharatKey)
            {
                mSound = MediaPlayer.create(SharatClass.this,Const.soundTrack.get("unlock"));
                mSound.start();
                openDoor();
            }
            else
            {
                mSound = MediaPlayer.create(SharatClass.this,Const.soundTrack.get("locked"));
                mSound.start();
            }
        });

        sw1.setOnClickListener(v -> {
            switchSound();
            checksolution();
        });
        sw2.setOnClickListener(v -> {
            switchSound();
            checksolution();
        });
        sw3.setOnClickListener(v -> {
            switchSound();
            checksolution();
        });
        sw4.setOnClickListener(v -> {
            switchSound();
            checksolution();
        });
        sw5.setOnClickListener(v -> {
            switchSound();
            checksolution();
        });
        sw6.setOnClickListener(v -> {
            switchSound();
            checksolution();
        });
        sw7.setOnClickListener(v -> {
            switchSound();
            checksolution();
        });
        sw8.setOnClickListener(v -> {
            switchSound();
            checksolution();
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