package com.arctouch.codechallenge.home.viewmodel;

import com.arctouch.codechallenge.home.view.HomeView;
import com.arctouch.codechallenge.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HomeViewModelTest {

    @Mock
    private HomeView mView;

    private HomeViewModel mViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mViewModel = new HomeViewModel(mView);
    }

    @Test
    public void moviesFetchedEmptyListTest(){
        mViewModel.moviesFetched(new ArrayList<>());
        verify(mView, atLeastOnce()).showLoading(false);
        verify(mView, atLeastOnce()).hasNewMovies(false);
    }

    @Test
    public void moviesFetchedTest(){
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(mock(Movie.class));
        mViewModel.moviesFetched(movies);
        verify(mView, atLeastOnce()).showLoading(false);
        verify(mView, atLeastOnce()).hasNewMovies(true);
    }

}