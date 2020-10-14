package com.movie.lister.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.movie.lister.R;
import com.movie.lister.ui.detail.DetailFragment;
import com.movie.lister.ui.list.ListFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
   private static final String TAG = "MainActivity";
   private MovieViewModel viewModel;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

      getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ListFragment()).commitNow();
   }

   @Override
   protected void onNewIntent(Intent intent) {
      super.onNewIntent(intent);
      if (Intent.ACTION_SEARCH.equals(intent.getAction()))
         viewModel.searchMovie(intent.getStringExtra(SearchManager.QUERY));
   }

   @Override
   public boolean onSupportNavigateUp() {
      onBackPressed();
      return super.onSupportNavigateUp();
   }

   public void onOpenMovieDetails() {
      getSupportFragmentManager().beginTransaction().add(android.R.id.content, new DetailFragment()).addToBackStack(null).commit();
   }

   @Override
   public void onBackPressed() {
      if (getSupportFragmentManager().getBackStackEntryCount() > 0)
         getSupportFragmentManager().popBackStack();
      else
         super.onBackPressed();

   }
}