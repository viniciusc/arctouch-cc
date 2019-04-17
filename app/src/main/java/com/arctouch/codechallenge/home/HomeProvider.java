package com.arctouch.codechallenge.home;

import android.annotation.SuppressLint;

import com.arctouch.codechallenge.BuildConfig;
import com.arctouch.codechallenge.data.Cache;
import com.arctouch.codechallenge.home.viewmodel.HomeViewModelInterface;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.api.ApiInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeProvider {

    private Long mPageIndex = 0L; //first page in first iteration
    private int mTotalPages = -1;
    private HomeViewModelInterface mViewModel;

    public HomeProvider(HomeViewModelInterface viewModel) {
        mViewModel = viewModel;
    }

    @SuppressLint("CheckResult")
    public void getGenres(){
        ApiInstance.getInstance().genres(BuildConfig.API_KEY, Locale.getDefault().getDisplayLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Cache.setGenres(response.genres);
                    mViewModel.genresFinished();
                });
    }

    @SuppressLint("CheckResult")
    public void loadMovies(){
        if(mTotalPages == -1 || mTotalPages > (mPageIndex + 1)){
            ApiInstance.getInstance().upcomingMovies(BuildConfig.API_KEY, Locale.getDefault().getDisplayLanguage(), getPageIndex())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        for (Movie movie : response.results) {
                            movie.genres = new ArrayList<>();
                            for (Genre genre : Cache.getGenres()) {
                                if (movie.genreIds.contains(genre.id)) {
                                    movie.genres.add(genre);
                                }
                            }
                        }
                        mViewModel.moviesFetched(response.results);
                        if (mTotalPages == -1) {
                            mTotalPages = response.totalPages;
                        }
                    });
        } else {
            mViewModel.moviesFetched(Collections.emptyList());
        }
    }

    /**
     * Keeps track of page number to be requested
     * @return long index
     */
    private Long getPageIndex(){
        mPageIndex ++;
        return mPageIndex;
    }
}
