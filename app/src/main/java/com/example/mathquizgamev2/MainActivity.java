package com.example.mathquizgamev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void guidePage(View view) {
        // In startGame() method, create an Intent to launch StartGame Activity
        Intent intent = new Intent(MainActivity.this, Guide.class);
        startActivity(intent);
        // Finish MainActivity
        finish();
    }
}