package com.example.ex07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private final String FILENAME = "text.txt";

    TextView tV;
    EditText eT;
    Button file;
    Button vET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tV = findViewById(R.id.tV);
        eT = findViewById(R.id.eT);
        file = findViewById(R.id.file);
        vET = findViewById(R.id.vET);
    }

    public void onClickRawFile(View view) {
        String fileName = FILENAME.substring(0, FILENAME.length() - 4);
        int resourceId = this.getResources().getIdentifier(fileName, "raw", this.getPackageName());

        try {
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            tV.setText(sB.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickText(View view) {
        String str = eT.getText().toString();
        tV.setText(str);
    }
}