package com.rgsvt.dicuciin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toDatalaundry(View v){
        Intent intent = new Intent(MainActivity.this, DataLaundryActivity.class);
        startActivity(intent);
    }

    public void toDataLaundryBaju(View v){
        Intent intent = new Intent(MainActivity.this, DataLaundryBajuActivity.class);
        startActivity(intent);
    }

    public void toCuciSepatuForm(View v) {
        Intent intent = new Intent(MainActivity.this, CuciSepatuActivity.class);
        startActivity(intent);
    }

    public void toCuciBajuForm(View v) {
        Intent intent = new Intent(MainActivity.this, CuciBajuActivity.class);
        startActivity(intent);
    }

    public void toCuciSelimutForm(View v) {
        Intent intent = new Intent(MainActivity.this, CuciSelimutActivity.class);
        startActivity(intent);
    }

    public void toCuciCelanaForm(View v) {
        Intent intent = new Intent(MainActivity.this, CuciCelanaActivity.class);
        startActivity(intent);
    }
}