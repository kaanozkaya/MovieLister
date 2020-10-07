package com.movie.lister.ui;

import android.os.Bundle;

import com.movie.lister.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
   private static final String TAG = "MainActivity";
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);


   }
}