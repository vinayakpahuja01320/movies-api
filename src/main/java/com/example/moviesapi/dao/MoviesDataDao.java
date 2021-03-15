package com.example.moviesapi.dao;

import com.example.moviesapi.models.MovieData;
import com.example.moviesapi.models.MovieDataPK;
import com.example.moviesapi.models.Movies;
import com.example.moviesapi.repo.MoviesDataLayer;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoviesDataDao {

  private final MoviesDataLayer moviesDataLayer;

  public MoviesDataDao(MoviesDataLayer moviesDataLayer) {
    this.moviesDataLayer = moviesDataLayer;
  }

  public List<Movies> getMovieData() {
    return moviesDataLayer.getMovies();
  }
  public MovieData getMoviesById(MovieDataPK movieDataPK){
    try {
      return moviesDataLayer.getOne(movieDataPK);
    }
    catch (Exception e){
      log.error("Not able to get the record",e);
      return null;
    }
  }

  public synchronized void addMovies(MovieData movieData) {
    moviesDataLayer.saveAndFlush(movieData);
  }


}
