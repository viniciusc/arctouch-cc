package com.arctouch.codechallenge.home.viewmodel;

import com.arctouch.codechallenge.home.HomeProvider;
import com.arctouch.codechallenge.home.view.HomeView;
import com.arctouch.codechallenge.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel implements HomeViewModelInterface {

    private HomeView mView;
    private List<Movie> mMovieList = new ArrayList<>();
    private HomeProvider mProvider;

    public HomeViewModel(HomeView view) {
        mView = view;
        mProvider = new HomeProvider(this);
    }

    public void init(){
        mProvider.getGenres();
    }

    @Override
    public void moviesFetched(List<Movie> list) {
        if(mMovieList == null || mMovieList.isEmpty()){
            mMovieList = new ArrayList<>();
        }
        mMovieList.addAll(list);
        mView.populateRecyclerView(mMovieList);
        mView.showLoading(false);

        if(list.size() > 0){
            mView.hasNewMovies(true);
        } else {
            mView.hasNewMovies(false);
        }
    }

    @Override
    public void genresFinished() {
        mProvider.loadMovies();
    }

    public void loadMoreItems(){
        mProvider.loadMovies();
    }


}
