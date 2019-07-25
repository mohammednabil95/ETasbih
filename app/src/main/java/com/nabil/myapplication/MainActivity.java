package com.nabil.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{
    RelativeLayout tap;
    int counter = 100;
    TextView tvcounter;
    TextView textView;
    Button btnduas, btnedit,btnprayer;
    ImageButton btnreset;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.increment_sound);
        tvcounter = findViewById(R.id.tvcounter);
        tap = findViewById(R.id.tap);
        btnduas = (Button) findViewById(R.id.btnduas);
        btnedit = findViewById(R.id.btnedit);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setFocusable(false);
                openDialog();
            }
        });
        btnreset = findViewById(R.id.btnreset);
        btnprayer=findViewById(R.id.btnprayer);
        textView = findViewById(R.id.textView);
        textView.setText("textview");
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
                vibrator.vibrate(50);
                if (counter != 0)
                    counter--;
                tvcounter.setText(Integer.toString(counter));
                if (counter == 0) {
                    vibrator.cancel();
                    mediaPlayer.start();
                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setFocusable(false);
                counter = 100;
                tvcounter.setText(Integer.toString(counter));
            }
        });
        btnduas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });

        btnprayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });
    }
    private void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "number");
    }

    @Override
    public void applynum(String editnumber) {
        tvcounter.setText(editnumber);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
