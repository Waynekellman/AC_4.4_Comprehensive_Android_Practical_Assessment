package com.nyc.ac_44_comprehensive_android_practical_assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DogsActivity extends AppCompatActivity {

    private static final String TAG = "DogsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);

        Intent intent = getIntent();
        Log.d(TAG, "onCreate: " + intent.getStringExtra("breedName"));
    }
}
