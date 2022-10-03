package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InSharatclass extends AppCompatActivity {

    private Button btn1, btn2, btn3 , btn4, btn5, btn6 ,btn7, btn8 ,btn9, btn0, btnR, btnE, btnCoffin, btnInSharatclassToSharatclass;
    private TextView tvPhonePass;
    private MediaPlayer mSound = null;
    private Boolean phoneKey = false;
    private String password = "2715";

    //////////
    public void btnNum() // Gives buttons their respective numbers
    {
        Button[] btnList = {btn0, btn1, btn2, btn3 , btn4, btn5, btn6 ,btn7, btn8 ,btn9};

        for (int i = 0; i < btnList.length; i++) {
            btnList[i].setText(String.valueOf(i));
        }
    }

    public void addNumber(Button btn) // Adds requested number to the password combination
    {
        btnSound();
        if(tvPhonePass.getText().toString().length() == 4)
            Toast.makeText(this, "Only 4 digits allowed", Toast.LENGTH_SHORT).show();
        else
        {
            String st = tvPhonePass.getText().toString();
            st += btn.getText().toString();
            tvPhonePass.setText(st);
        }
    }

    public void btnSound()
    {
        mSound = MediaPlayer.create(InSharatclass.this, Const.soundTrack.get("beep"));
        mSound.start();
    }

    public void checkPass() // Checks if the password is correct
    {
        if(tvPhonePass.getText().toString().equals(password)) {
            mSound = MediaPlayer.create(InSharatclass.this, Const.soundTrack.get("sharat puzzle solved"));
            mSound.start();
            phoneKey = true;
            btnCoffin.setBackgroundResource(R.drawable.keyy);

        }
        else {
            mSound = MediaPlayer.create(InSharatclass.this, Const.soundTrack.get("wrong"));
            mSound.start();
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
            reset();
        }

    }

    public void reset() // Resets password input
    {
        mSound = MediaPlayer.create(InSharatclass.this, Const.soundTrack.get("hint"));
        mSound.start();
        tvPhonePass.setText("");
    }

    public void isAllSolved() //Checks if you solved this puzzle before
    {
        if(Const.approomKey) {
            btnCoffin.setVisibility(View.INVISIBLE);

            View[] vList = {btn0, btn1, btn2, btn3 , btn4, btn5, btn6 ,btn7, btn8 ,btn9, btnE, btnR, tvPhonePass};

            for (View v : vList)
                v.setEnabled(false);
            tvPhonePass.setText(password);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_sharatclass);

        getSupportActionBar().setTitle("In sharat classroom");

        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnR = findViewById(R.id.btn_r);
        btnE = findViewById(R.id.btn_e);
        btnCoffin = findViewById(R.id.btn_coffin);
        tvPhonePass = findViewById(R.id.tv_phone_pass);
        btnInSharatclassToSharatclass = findViewById(R.id.btn_in_sharatclass_to_sharatclass);

        isAllSolved();

        btnNum();


        btn0.setOnClickListener(v -> addNumber(btn0));
        btn1.setOnClickListener(v -> addNumber(btn1));
        btn2.setOnClickListener(v -> addNumber(btn2));
        btn3.setOnClickListener(v -> addNumber(btn3));
        btn4.setOnClickListener(v -> addNumber(btn4));
        btn5.setOnClickListener(v -> addNumber(btn5));
        btn6.setOnClickListener(v -> addNumber(btn6));
        btn7.setOnClickListener(v -> addNumber(btn7));
        btn8.setOnClickListener(v -> addNumber(btn8));
        btn9.setOnClickListener(v -> addNumber(btn9));

        btnR.setOnClickListener(v -> reset());
        btnE.setOnClickListener(v -> checkPass());

        btnInSharatclassToSharatclass.setOnClickListener(v -> {
            Intent it = new Intent(InSharatclass.this,SharatClass.class);
            startActivity(it);
        });

        btnCoffin.setOnClickListener(v -> {
            if(phoneKey) {
                mSound = MediaPlayer.create(InSharatclass.this, Const.soundTrack.get("grab"));
                mSound.start();
                Const.approomKey = true;
                btnCoffin.setVisibility(View.INVISIBLE);
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