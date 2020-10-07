package com.movie.lister.data.network;

import com.movie.lister.data.model.MovieDetails;
import com.movie.lister.data.model.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface WebServiceInterface {

   @GET("search/movie/")
   Call<SearchResult> searchMovie(@Query("query") String queryText, @Query("api_key") String apiKey);

   @GET("movie/{id}")
   Call<MovieDetails> queryMovieDetails(@Path("id") int id, @Query("append_to_response") String appendToResponse, @Query("api_key") String apiKey);

}
