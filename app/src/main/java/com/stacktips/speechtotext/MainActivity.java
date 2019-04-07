package com.stacktips.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    private String s;
    private Button button ;
    private String firstLetter;

    FileInputStream fis = null;

    String[] locations = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                openlogin_information();
            }
        });
    }
    public void openlogin_information()
    {
        Intent intent=new Intent(this,Login_Information.class);
        startActivity(intent);
    }



    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //mVoiceInputTv.setText(result.get(0));
                    s=result.get(0).toLowerCase();
                    try {
                        fis = openFileInput("grocery3.txt");
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        String text;

                        while ((text = br.readLine()) != null) {
                            if (text.toLowerCase().contains(s)) {
                                locations = text.split(" ");
                                firstLetter= locations[0].substring(0,1).toUpperCase();
                                mVoiceInputTv.setText(firstLetter+locations[0].substring(1,locations[0].length())+" can be found in aisle " + locations[1] + ", section " + locations[2]
                                        + ", shelf " + locations[3] + ".");
                                if(locations.length>4)
                                {
                                    firstLetter= locations[0].substring(0,1).toUpperCase();
                                    mVoiceInputTv.setText(firstLetter+locations[0].substring(1,locations[0].length())+ " "+locations[1]+" can be found in aisle " + locations[2] + ", section " + locations[3] + ", shelf " + locations[4] + ".");
                                }
                                break;
                            }


                            else
                            {
                                mVoiceInputTv.setText("Sorry, this product is unavailable, please try again.");
                            }

                        }

                    }catch (FileNotFoundException e)
                    {
                        mVoiceInputTv.setText("File not found when attempting to create the file...");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally
                    {
                        if (fis != null)
                        {
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }


                break;
            }

        }
    }



}
