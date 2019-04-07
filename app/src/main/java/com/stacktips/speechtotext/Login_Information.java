package com.stacktips.speechtotext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Information extends AppCompatActivity
{
    private EditText name;
    private EditText pass;
    private Button log;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_information);

        name = (EditText) findViewById(R.id.UserName);
        pass = (EditText) findViewById(R.id.UserPass);
        log = (Button) findViewById(R.id.logButton);
        text = (TextView) findViewById(R.id.tv);

        log.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                validate(name.getText().toString(),pass.getText().toString());
            }
        });


    }
    @SuppressLint("SetTextI18n")
    private void validate(String name, String pass)
    {
        if((name.equals("Aiman")) && (pass.equals("akks")))
        {
            Intent intent = new Intent(this,act4.class);
            startActivity(intent);
        }
        else
        {
            text.setText("The password or username entered is incorrect.");
        }
    }
}
