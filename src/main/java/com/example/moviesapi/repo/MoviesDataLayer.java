package com.example.moviesapi.repo;

import com.example.moviesapi.models.MovieData;
import com.example.moviesapi.models.MovieDataPK;
import com.example.moviesapi.models.Movies;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesDataLayer extends JpaRepository<MovieData, MovieDataPK> {
  @Query(value =
             "select new com.example.moviesapi.models.Movies (c.state,c.city,cd.cinemaName,m.movieName,m.movieTiming,m.totalSeats,m.availableSeats) from GeoData c , Cinema cd, MovieData m where c.cinemaId=cd.cinemaId and cd.cinemaId=m.cinemaId")
  List<Movies> getMovies();
}
