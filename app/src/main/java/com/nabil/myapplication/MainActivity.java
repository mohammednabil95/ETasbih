package com.nabil.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Vibrator;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    RelativeLayout tap;
    int counter = 100;
    TextView tvCounter;
    TextView textView;
    Button btnDua, btnEdit, btnPrayer;
    ImageButton btnReset;
    Vibrator vibrator;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.increment_sound);

        tvCounter = findViewById(R.id.tvCounter);
        tap = findViewById(R.id.tap);
        btnDua = findViewById(R.id.btnDuas);
        btnEdit = findViewById(R.id.btnEdit);
        btnReset = findViewById(R.id.btnReset);
        btnPrayer = findViewById(R.id.btnPrayer);
        textView = findViewById(R.id.textView);
        dialog=new Dialog(this);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setFocusable(false);
                openDialog();
            }
        });

        textView.setText("textView");
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        Intent intent = getIntent();
        String id = intent.getStringExtra("text_1");
        textView.setText(id);
        textView.setFocusable(false);

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                textView.setFocusableInTouchMode(true);
                return false;
            }
        });

        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setFocusable(false);
                SharedPreferences sharedPreferences=getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
                boolean check=sharedPreferences.getBoolean("vib",true);
                if (check==false){
                    vibrator.cancel();
                    if (counter != 0)
                        counter--;
                    tvCounter.setText(Integer.toString(counter));
                    if (counter == 0) {
                        vibrator.cancel();
                        mediaPlayer.start();
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    vibrator.vibrate(50);
                    if (counter != 0)
                        counter--;
                    tvCounter.setText(Integer.toString(counter));
                    if (counter == 0) {
                        vibrator.cancel();
                        mediaPlayer.start();
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setFocusable(false);
                counter = 100;
                tvCounter.setText(Integer.toString(counter));
            }
        });

        btnDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });

        btnPrayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, PrayerActivity.class));
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });
    }

    private void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "number");
    }

    @Override
    public void applyNum(String editNumber) {
        tvCounter.setText(editNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSettings:
                OpenSettings();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void OpenSettings() {
        dialog.setContentView(R.layout.settings_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        final SharedPreferences sharedPreferences;
        SwitchMaterial switchMaterial;
        Button btnOK;

        switchMaterial=dialog.findViewById(R.id.switchMaterial);
        btnOK=dialog.findViewById(R.id.btnOK);

        sharedPreferences=getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        switchMaterial.setChecked(sharedPreferences.getBoolean("vib",true));

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor.putBoolean("vib",true);
                }else {
                    editor.putBoolean("vib",false);
                }
                editor.apply();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
