package com.firebase.ameerhamza.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REQ_CODE_SPEECH_OUTPUT = 143;
    ImageView btnSpeak;
    TextView txvResult;
    ImageView loadOne, loadTwo, loadThree;
    public static final String LOAD_ONE = "load one";
    public static final String LOAD_TWO = "load two";
    public static final String LOAD_THREE = "load three";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSpeak = findViewById(R.id.btnSpeak);
        txvResult = findViewById(R.id.txvResult);
        loadOne = findViewById(R.id.loadOne);
        loadTwo = findViewById(R.id.loadTwo);
        loadThree = findViewById(R.id.loadThree);

        loadOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvResult.setText(LOAD_ONE);
            }
        });

        loadTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvResult.setText(LOAD_TWO);
            }
        });

        loadThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvResult.setText(LOAD_THREE);
            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });
    }

    private void open() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);
        } else {
            Toast.makeText(this, "Your device not support Text Input Speech", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: " + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        String s = "";
        switch (requestCode) {
            case REQ_CODE_SPEECH_OUTPUT:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(voiceInText.get(0));
                }
                break;
            default:
                Toast.makeText(this, "on activity not working", Toast.LENGTH_LONG).show();
        }
    }
}
