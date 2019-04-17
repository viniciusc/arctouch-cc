package com.arctouch.codechallenge.details.viewmodel;

import com.arctouch.codechallenge.details.DetailsProvider;
import com.arctouch.codechallenge.details.view.DetailsView;
import com.arctouch.codechallenge.model.Movie;

public class DetailsViewModel implements DetailsViewModelInterface {

    private DetailsView mView;
    private DetailsProvider mProvider;

    public DetailsViewModel(DetailsView view, int id) {
        mView = view;
        mProvider = new DetailsProvider(this);
        getMovieById(id);
    }

    public void getMovieById(int id){
        mProvider.getMovie(id);
    }

    @Override
    public void movieDetails(Movie movie) {
        mView.populateViewElements(movie);
    }
}
