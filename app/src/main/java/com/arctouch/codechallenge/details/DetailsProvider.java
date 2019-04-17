package com.arctouch.codechallenge.details;

import android.annotation.SuppressLint;

import com.arctouch.codechallenge.BuildConfig;
import com.arctouch.codechallenge.details.viewmodel.DetailsViewModelInterface;
import com.arctouch.codechallenge.api.ApiInstance;

import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailsProvider {

    private DetailsViewModelInterface mViewModel;

    public DetailsProvider(DetailsViewModelInterface viewModel) {
        mViewModel = viewModel;
    }

    @SuppressLint("CheckResult")
    public void getMovie(int id){
        ApiInstance.getInstance().movie(Long.valueOf(id), BuildConfig.API_KEY, Locale.getDefault().getDisplayLanguage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> mViewModel.movieDetails(response));
    }
}
