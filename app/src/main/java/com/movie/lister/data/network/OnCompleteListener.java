package com.movie.lister.data.network;

public interface OnCompleteListener<T> {
   void onComplete(boolean isSuccessful, T model);
}
