package com.arctouch.codechallenge.home.viewmodel;

import com.arctouch.codechallenge.model.Movie;

import java.util.List;

public interface HomeViewModelInterface {
    void genresFinished();
    void moviesFetched(List<Movie> list);
}
