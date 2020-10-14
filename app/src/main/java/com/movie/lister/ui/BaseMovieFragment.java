package com.movie.lister.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseMovieFragment<T extends ViewDataBinding> extends Fragment {
   public MovieViewModel viewModel;
   public T binding;
   public Context context;

   public abstract int getLayoutId();

   @Override
   public void onAttach(@NonNull Context context) {
      super.onAttach(context);
      this.context = context;
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
      viewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
      binding.setVariable(com.movie.lister.BR.viewModel, viewModel);
      binding.setLifecycleOwner(this);
      return binding.getRoot();
   }



}
