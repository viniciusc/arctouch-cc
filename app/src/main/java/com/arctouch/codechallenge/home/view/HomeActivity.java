package com.arctouch.codechallenge.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.details.view.DetailsActivity;
import com.arctouch.codechallenge.home.viewmodel.HomeViewModel;
import com.arctouch.codechallenge.model.Movie;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeView, MovieListClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private HomeViewModel mViewModel;
    private HomeAdapter mAdapter;
    private List<Movie> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        this.recyclerView = findViewById(R.id.recyclerView);
        this.progressBar = findViewById(R.id.progressBar);

        mViewModel = new HomeViewModel(this);
        recyclerView.addOnScrollListener(getScrollDetector());
        mViewModel.init();
    }


    @Override
    public void showLoading(boolean show) {
        if(show){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void populateRecyclerView(List<Movie> list) {
        if(mList == null || mList.isEmpty()){
            mList = list;
            mAdapter = new HomeAdapter(mList, this);
            recyclerView.setAdapter(mAdapter);
        } else {
            mList = list;
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Basic feedback to loading more movies
     * @param newMovies true if there are new movies to the list, false otherwise
     */
    @Override
    public void hasNewMovies(boolean newMovies){
        if(newMovies){
            Toast.makeText(HomeActivity.this, "Loaded", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HomeActivity.this, "No new movies", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Listener that detects recyclerView was scrolled to its bottom
     * @return Scroll listener that requests more items
     */
    public RecyclerView.OnScrollListener getScrollDetector(){
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (layoutManager != null &&
                        layoutManager.findLastCompletelyVisibleItemPosition() == mAdapter.getItemCount() - 1) {
                    showLoading(true);
                    mViewModel.loadMoreItems();
                }
            }
        };
    }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("movieId", mList.get(position).id);
        startActivity(intent);
    }
}
