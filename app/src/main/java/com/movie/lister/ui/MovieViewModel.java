package com.movie.lister.ui;

import com.movie.lister.data.model.Cast;
import com.movie.lister.data.model.Crew;
import com.movie.lister.data.model.Genre;
import com.movie.lister.data.model.Movie;
import com.movie.lister.data.model.MovieDetails;
import com.movie.lister.data.network.WebService;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {
   private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
   private MutableLiveData<MovieDetails> movieDetailsLiveData = new MutableLiveData<>();
   private ObservableField<Boolean> isSearching = new ObservableField<>();
   public String selectedMovieTitle;
   public ObservableField<String> genres = new ObservableField<>();
   public ObservableField<String> runtime = new ObservableField<>();
   public ObservableField<String> director = new ObservableField<>();
   public ObservableField<String> starring = new ObservableField<>();

   public void searchMovie(String query) {
      isSearching.set(true);
      WebService.getInstance().searchMovie(query, (isSuccessful, searchResult) -> {
         isSearching.set(false);
         if (isSuccessful)
            moviesLiveData.postValue(searchResult.getMovies());
      });
   }

   public LiveData<List<Movie>> getMovies() {
      return moviesLiveData;
   }

   public ObservableField<Boolean> isSearching() {
      return isSearching;
   }

   public LiveData<MovieDetails> getMovieDetails() {
      return movieDetailsLiveData;
   }

   public void queryMovieDetails(int movieId) {
      isSearching.set(true);
      WebService.getInstance().queryMovieDetails(movieId, (isSuccessful, movieDetails) -> {
         isSearching.set(false);
         if (isSuccessful){
            movieDetailsLiveData.postValue(movieDetails);
            setDetailTexts(movieDetails);
         }
      });
   }

   private void setDetailTexts(MovieDetails movieDetails) {
      genres.set(getGenreText(movieDetails.getGenres()));
      runtime.set(getRuntimeText(movieDetails.getRuntime()));
      director.set(getDirectorText(movieDetails.getCredits().getCrew()));
      starring.set(getStarringText(movieDetails.getCredits().getCast()));
   }

   private String getStarringText(List<Cast> cast) {
      String starring = "";
      for (int i = 0; i < cast.size(); i++) {
         if (starring.isEmpty())
            starring += cast.get(i).getName();
         else starring += ", " + cast.get(i).getName();
         if (i == 3) break;
      }
      return starring;
   }

   private String getDirectorText(List<Crew> crew) {
      String directors = "";
      for (int i = 0; i < crew.size(); i++) {
         if (crew.get(i).getJob().toLowerCase().equals("director"))
            if (directors.isEmpty())
               directors += crew.get(i).getName();
            else
               directors += ", " + crew.get(i).getName();
      }

      return directors;
   }

   private String getRuntimeText(Long runtime) {
      if (runtime == null)
         return "0m";
      int hours, minutes;
      hours = (int) (runtime / 60);
      minutes = (int) (runtime % 60);
      return (runtime >= 60 ? hours + "h " : "") + minutes + "m";
   }

   private String getGenreText(List<Genre> genres) {
      String formattedGenre = "";
      for (int i = 0; i < genres.size(); i++) {
         if (formattedGenre.isEmpty())
            formattedGenre += genres.get(i).getName();
         else formattedGenre += ", " + genres.get(i).getName();
         if (i == 4) break;
      }
      return formattedGenre;
   }
}
