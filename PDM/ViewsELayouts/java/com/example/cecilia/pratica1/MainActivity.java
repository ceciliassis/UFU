package com.example.cecilia.pratica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String layout = "complex";
        switch (layout){
            case "frame":
                setContentView(R.layout.framelayout);
                break;
            case "linear":
                setContentView(R.layout.linearlayout);
                break;
            case "table":
                setContentView(R.layout.tablelayout);
                break;
            case "relative":
                setContentView(R.layout.relativelayout);
                break;
            case "absolute":
                setContentView(R.layout.absolutelayout);
                break;
            case "complex":
                setContentView(R.layout.complexlayout);
                break;
        }
    }
}
