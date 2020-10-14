package com.movie.lister.ui.list;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movie.lister.R;
import com.movie.lister.data.model.Movie;
import com.movie.lister.databinding.FragmentListBinding;
import com.movie.lister.ui.BaseMovieFragment;
import com.movie.lister.ui.MainActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ListFragment extends BaseMovieFragment<FragmentListBinding> {

   @Override
   public int getLayoutId() {
      return R.layout.fragment_list;
   }


   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
      setHasOptionsMenu(true);
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = super.onCreateView(inflater, container, savedInstanceState);
      initRecycler();
      viewModel.getMovies().observe(getViewLifecycleOwner(), this::setListData);

      return view;
   }

   private void initRecycler() {
      binding.recycler.setLayoutManager(new LinearLayoutManager(context));
      addDivider();
   }

   private void addDivider() {
      DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
      divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_recycler));
      binding.recycler.addItemDecoration(divider);
   }

   private void setListData(List<Movie> movies) {
      if (movies == null) return;
      MovieListAdapter adapter = new MovieListAdapter(getLayoutInflater(), movies, (title,movieId) -> {
         viewModel.queryMovieDetails(movieId);
         viewModel.selectedMovieTitle = title;
         ((MainActivity) getActivity()).onOpenMovieDetails();
      });
      binding.recycler.setAdapter(adapter);
   }

   @Override
   public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
      super.onCreateOptionsMenu(menu, inflater);
      inflater.inflate(R.menu.menu_search,menu);

      SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
      SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
      searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
   }
}
