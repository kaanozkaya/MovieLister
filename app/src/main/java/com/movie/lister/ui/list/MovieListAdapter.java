package com.movie.lister.ui.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movie.lister.MyApplication;
import com.movie.lister.R;
import com.movie.lister.data.model.Movie;
import com.movie.lister.databinding.RowMovieBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
   private static final String BASE_URL_POSTER = "http://image.tmdb.org/t/p/w92/";
   private LayoutInflater inflater;
   private List<Movie> movies;
   private final OnOpenMovieDetailsListener onOpenMovieDetailsListener;

   public MovieListAdapter(LayoutInflater inflater, List<Movie> movies, OnOpenMovieDetailsListener onOpenMovieDetailsListener) {
      this.inflater = inflater;
      this.movies = movies;
      this.onOpenMovieDetailsListener = onOpenMovieDetailsListener;
   }

   @NonNull
   @Override
   public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      RowMovieBinding binding = DataBindingUtil.inflate(inflater, R.layout.row_movie, parent, false);
      setMaxLinesToEllipsize(binding.textViewOverview);
      return new MyViewHolder(binding);
   }

   private void setMaxLinesToEllipsize(TextView textView) {
      float actualTextHeightInDp = textView.getTextSize() / MyApplication.getInstance().getResources().getDisplayMetrics().density;
      int maxLineCount = (int) Math.floor(76f / actualTextHeightInDp / 1.2) - 1; // 96dp each row, 20dp for margins, 1 line for title, multiply line height by 1.2 for line spacings
      textView.setMaxLines(maxLineCount);
   }

   @Override
   public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      Movie movie = movies.get(position);
      holder.binding.setMovie(movie);
      Glide.with(holder.binding.imageViewPoster).load(BASE_URL_POSTER + movie.getPosterPath()).into(holder.binding.imageViewPoster);

      holder.binding.getRoot().setOnClickListener(view -> onOpenMovieDetailsListener.onOpenMovieDetails(movie.getTitle(),movie.getId()));
   }

   @Override
   public int getItemCount() {
      return movies.size();
   }

   public static class MyViewHolder extends RecyclerView.ViewHolder {
      public RowMovieBinding binding;

      public MyViewHolder(@NonNull RowMovieBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
      }
   }
}
