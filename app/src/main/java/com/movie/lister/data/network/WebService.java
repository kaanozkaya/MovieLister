package com.movie.lister.data.network;

import com.movie.lister.data.model.MovieDetails;
import com.movie.lister.data.model.SearchResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {
   private static final String API_KEY = "2696829a81b1b5827d515ff121700838";
   private static WebService instance;
   private WebServiceInterface webServiceInterface;

   private WebService() {
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl("http://api.themoviedb.org/3/")
              .addConverterFactory(GsonConverterFactory.create())
              .build();

      webServiceInterface = retrofit.create(WebServiceInterface.class);
   }

   public static WebService getInstance() {
      if (instance == null)
         instance = new WebService();

      return instance;
   }

   public void searchMovie(String queryText, OnCompleteListener<SearchResult> listener) {
      enqueue(webServiceInterface.searchMovie(queryText, API_KEY), listener);
   }

   public void queryMovieDetails(int id, OnCompleteListener<MovieDetails> listener) {
      enqueue(webServiceInterface.queryMovieDetails(id, "videos,credits", API_KEY), listener);
   }

   private <T> void enqueue(Call<T> call, final OnCompleteListener<T> listener) {
      call.enqueue(new Callback<T>() {
         @Override
         public void onResponse(Call<T> call, Response<T> response) {
            listener.onComplete(response.isSuccessful(), response.body());
         }

         @Override
         public void onFailure(Call<T> call, Throwable t) {
            listener.onComplete(false, null);

         }
      });

   }
}
