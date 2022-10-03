package com.example.saveiossy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ComplibScreen extends AppCompatActivity {
    private Button btnEnterPass, btnOpenImage, btnOpenDialog, btnComplibScreenToComplib;
    private EditText etCompPass;
    private TextView tvCompScreen, tvBinaryPass;
    private RelativeLayout layout = null;
    private MediaPlayer mSound = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Library's Computer");

        setContentView(R.layout.activity_complib_screen);
        btnEnterPass = findViewById(R.id.btn_enter_pass);
        btnOpenDialog = findViewById(R.id.btn_open_dialog);
        btnOpenImage = findViewById(R.id.btn_open_image);
        etCompPass = findViewById(R.id.et_comp_pass);
        tvCompScreen = findViewById(R.id.tv_comp_screen);
        tvBinaryPass = findViewById(R.id.tv_binary_pass);

        btnComplibScreenToComplib = findViewById(R.id.btn_complib_screen_to_complib);
        layout = findViewById(R.id.complib_screen);

        btnEnterPass.setOnClickListener(v -> {
            if(etCompPass.getText().toString().equals("110238")) { //Checks if password is correct
                layout.setBackground(getResources().getDrawable(R.drawable.compscreenunlock2));
                btnOpenImage.setVisibility(View.VISIBLE);
                btnOpenDialog.setVisibility(View.VISIBLE);
                findViewById(R.id.btn_in_sharatclass_to_sharatclass);

                View[] views = { btnEnterPass, etCompPass, tvCompScreen, tvBinaryPass};
                for (View vScreen : views) {
                    vScreen.setVisibility(View.INVISIBLE);
                }

                mSound = MediaPlayer.create(ComplibScreen.this,R.raw.windowsxpstartup);
                mSound.start();
            }
            if(etCompPass.getText().toString().equals("173451")) { //Checks if secret password is correct
                mSound = MediaPlayer.create(ComplibScreen.this,R.raw.windowsxpshutdown);
                mSound.start();
                Intent it = new Intent(ComplibScreen.this,Loading.class);
                startActivity(it);
            }
            else // wrong password
            {
                mSound = MediaPlayer.create(ComplibScreen.this, Const.soundTrack.get("win xp error"));
                mSound.start();
                etCompPass.setText("");
                Toast.makeText(ComplibScreen.this, "Wrong", Toast.LENGTH_SHORT).show();

            }
        });

        btnComplibScreenToComplib.setOnClickListener(v -> {
            Intent it = new Intent(ComplibScreen.this,Complib.class);
            startActivity(it);
        });

        // Opens pigeon image
        btnOpenImage.setOnClickListener(v -> {
            Intent it = null;
            String link = "https://www.bing.com/images/search?view=detailV2&id=62922EE07CC61FA2D2FD46CF8DDE50AE0E9637F0&thid=OIP.OfQszfttoT8hqcXGIixNKQHaE8&mediaurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F4%2F4d%2FRock_Pigeon_Columba_livia.jpg%2F1200px-Rock_Pigeon_Columba_livia.jpg&exph=800&expw=1200&q=pigeon&selectedindex=3&ajaxhist=0&vt=0&eim=1,2,6";
            it = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(it);
        });
        // Opens Alert Dialog
        btnOpenDialog.setOnClickListener(v -> {
            final AlertDialog dialog=new AlertDialog.Builder(ComplibScreen.this).create();
            dialog.setIcon(R.drawable.flor);
            dialog.setTitle("About the google button");
            dialog.setMessage("The code to the door locking Iossy is found there");
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
            dialog.setCancelable(false);
            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialog.dismiss();
                }
            });
            dialog.show();
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