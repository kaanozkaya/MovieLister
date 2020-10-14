package com.movie.lister.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.movie.lister.R;
import com.movie.lister.databinding.FragmentDetailBinding;
import com.movie.lister.ui.BaseMovieFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailFragment extends BaseMovieFragment<FragmentDetailBinding> {
   public static final String BASE_URL_POSTER = "http://image.tmdb.org/t/p/w500/";
   @Override
   public int getLayoutId() {
      return R.layout.fragment_detail;
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = super.onCreateView(inflater, container, savedInstanceState);
      viewModel.getMovieDetails().observe(getViewLifecycleOwner(), movieDetails ->
         Glide.with(context).load(BASE_URL_POSTER + movieDetails.getPosterPath()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.imageViewPoster)
      );
      return view;
   }


   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      setHasOptionsMenu(true);
   }

   @Override
   public void onPrepareOptionsMenu(@NonNull Menu menu) {
      super.onPrepareOptionsMenu(menu);
      menu.findItem(R.id.action_search).setVisible(false);
   }

}
