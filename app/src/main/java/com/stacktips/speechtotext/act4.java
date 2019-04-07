package com.stacktips.speechtotext;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class act4 extends AppCompatActivity {

    TextInputLayout product;
    TextInputLayout aisle;
    TextInputLayout section;
    TextInputLayout shelf;

    TextView exception;

    String productText, aisleText, sectionText, shelfText;

    FileOutputStream fos = null;
    FileInputStream fis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act4);
        exception = (TextView) findViewById(R.id.exception);
    }

    public void buttonOnClick(View v)
    {
        product = (TextInputLayout) findViewById(R.id.productName);
        aisle = (TextInputLayout) findViewById(R.id.aisleNumber);
        section = (TextInputLayout) findViewById(R.id.section);
        shelf = (TextInputLayout) findViewById(R.id.shelfNumber);

        productText = product.getEditText().getText().toString();
        aisleText = aisle.getEditText().getText().toString();
        sectionText = section.getEditText().getText().toString();
        shelfText = shelf.getEditText().getText().toString();

        try
        {
            fos = openFileOutput("grocery3.txt", MODE_APPEND);
            fos.write(productText.getBytes());
            fos.write(" ".getBytes());
            fos.write(aisleText.getBytes());
            fos.write(" ".getBytes());
            fos.write(sectionText.getBytes());
            fos.write(" ".getBytes());
            fos.write(shelfText.getBytes());
            fos.write("\n".getBytes());

            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "grocery3.txt", Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e)
        {
            exception.setText("File not found when attempting to create the file...");
        }
        catch (Exception e)
        {
            exception.setText("something else is wrong");
        }
        finally
        {
            if (fos != null)
            {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void resetFields(View v)
    {
        finish();
        startActivity(getIntent());

//        product.getEditText().getText().clear();
//        aisle.getEditText().getText().clear();
//        section.getEditText().getText().clear();
//        shelf.getEditText().getText().clear();
    }
}