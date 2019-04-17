package com.arctouch.codechallenge.home.view;

import com.arctouch.codechallenge.model.Movie;

import java.util.List;

public interface HomeView {
    void showLoading(boolean show);
    void populateRecyclerView(List<Movie> list);
    void hasNewMovies(boolean newMovies);
}
