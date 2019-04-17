package com.arctouch.codechallenge.details.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.details.viewmodel.DetailsViewModel;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity implements DetailsView {

    private DetailsViewModel mViewModel;
    private MovieImageUrlBuilder mMovieImageUrlBuilder;
    private ImageView mPosterImage;
    private ImageView mBackdropImage;
    private TextView mName;
    private TextView mGenres;
    private TextView mRelease;
    private TextView mOverview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        mPosterImage = findViewById(R.id.poster_image);
        mBackdropImage = findViewById(R.id.backdrop_image);
        mName = findViewById(R.id.movie_title);
        mGenres = findViewById(R.id.movie_genres);
        mRelease = findViewById(R.id.movie_release_date);
        mOverview = findViewById(R.id.movie_overview);

        mMovieImageUrlBuilder = new MovieImageUrlBuilder();
        int id = (int) getIntent().getSerializableExtra("movieId");
        mViewModel = new DetailsViewModel(this, id);

    }

    @Override
    public void populateViewElements(Movie movie) {
        if(movie.backdropPath != null){
            Glide.with(this)
                    .load(mMovieImageUrlBuilder.buildBackdropUrl(movie.backdropPath))
                    .into(mBackdropImage);
        }
        if(movie.posterPath != null){
            Glide.with(this)
                    .load(mMovieImageUrlBuilder.buildPosterUrl(movie.posterPath))
                    .into(mPosterImage);
        }

        mName.setText(movie.title);
        mRelease.setText(movie.releaseDate);

        StringBuilder builder = new StringBuilder();
        builder.append(movie.genres.get(0));
        if(movie.genres.size() > 1){
            for (int i = 1; i < movie.genres.size(); i++) {//commas between genres
                builder.append(", ");
                builder.append(movie.genres.get(i).name);
            }
        }
        String genres = builder.toString();

        mGenres.setText(genres);
        mOverview.setText(movie.overview);
    }
}
