package com.example.moviesapi.service;

import com.example.moviesapi.dao.CinemaDataDao;
import com.example.moviesapi.dao.GeoDataDao;
import com.example.moviesapi.dao.MoviesDataDao;
import com.example.moviesapi.models.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoviesService {

  public static final String SPLITTER = ",";
  private final MoviesDataDao moviesData;
  private final CinemaDataDao cinemaDataDao;
  private final GeoDataDao geoDataDao;


  private MoviesService(MoviesDataDao moviesData,
                        CinemaDataDao cinemaDataDao,
                        GeoDataDao geoDataDao) {
    this.moviesData = moviesData;
    this.cinemaDataDao = cinemaDataDao;
    this.geoDataDao = geoDataDao;
  }

  public Map<String, Map<String, Map<String, Map<String, List<Movies>>>>> getMovies() {
    return moviesData.getMovieData()
        .stream()
        .collect(Collectors.groupingBy(Movies::getState,
                                       Collectors.groupingBy(Movies::getCity,
                                                             Collectors.groupingBy(Movies::getCinemaName,
                                                                                   Collectors.groupingBy(
                                                                                       Movies::getMovieName)))));

  }

  public String insertMovie(MoviesModel moviesModel) {
    String cinemaId = cinemaDataDao.getCinemaId(moviesModel.getCinemaName(),
                                                moviesModel.getCityName());
    if (cinemaId == null) {
      return "Cinema is not registerd";
    }
    MovieData movieData = MovieData.builder()
        .cinemaId(cinemaId)
        .movieName(moviesModel.getMovieName())
        .movieTiming(moviesModel.getMovieTiming())
        .availableSeats(getSeats(moviesModel.getAvailableSeats()))
        .totalSeats(moviesModel.getTotalSeats())
        .build();
    moviesData.addMovies(movieData);
    return "Movies added";
  }

  public void registerCinema(RegisterCinema registerCinema) {
    String cinemaId = UUID.randomUUID().toString();
    GeoData geoData = GeoData.builder()
        .cinemaId(cinemaId)
        .city(registerCinema.getCity())
        .state(registerCinema.getState())
        .build();
    Cinema cinema = Cinema.builder()
        .cinemaName(registerCinema.getCinemaName())
        .cinemaId(cinemaId)
        .cityName(registerCinema.getCity())
        .build();
    geoDataDao.insertData(geoData);
    cinemaDataDao.insertData(cinema);
  }


  public boolean areSeatsAvailable(String dbSeats, List<String> bookedSeats) {
    List<String> availableSeats = Arrays.stream(dbSeats.split(SPLITTER))
        .collect(Collectors.toList());
    return availableSeats.containsAll(bookedSeats);
  }

  public int totalSeats(String dbSeats, List<String> bookedSeats) {
    List<String> availableSeats = Arrays.stream(dbSeats.split(SPLITTER))
        .collect(Collectors.toList());
    availableSeats.removeAll(bookedSeats);
    return availableSeats.size();
  }

  public List<String> availableSeats(String dbSeats, List<String> bookedSeats) {
    List<String> availableSeats = Arrays.stream(dbSeats.split(SPLITTER))
        .collect(Collectors.toList());
    availableSeats.removeAll(bookedSeats);
    return availableSeats;
  }

  public String bookTicket(BookedSeats bookedSeats) {
    String cinemaID = cinemaDataDao.getCinemaId(bookedSeats.getCinemaName(),
                                                bookedSeats.getCityName());
    MovieDataPK movieDataPK = new MovieDataPK(cinemaID,
                                              bookedSeats.getMovieName());
    MovieData dbMovieData = moviesData.getMoviesById(movieDataPK);
    if (dbMovieData == null) {
      return String.format("No movie available with name:%s", bookedSeats.getMovieName());
    }
    if (!areSeatsAvailable(dbMovieData.getAvailableSeats(), bookedSeats.getBookedSeats())) {
      return "Seats not available";
    }

    MovieData movieData = MovieData.builder()
        .movieName(bookedSeats.getMovieName())
        .movieTiming(dbMovieData.getMovieTiming())
        .availableSeats(getAvailableSeats(bookedSeats, dbMovieData))
        .cinemaId(cinemaID)
        .totalSeats(totalSeats(dbMovieData.getAvailableSeats(), bookedSeats.getBookedSeats()))
        .build();
    moviesData.addMovies(movieData);
    return "Seats Confirmed";
  }

  private String getAvailableSeats(BookedSeats bookedSeats, MovieData dbMovieData) {
    List<String> availableSeats = availableSeats(dbMovieData.getAvailableSeats(),
                                                 bookedSeats.getBookedSeats());
    return getSeats(availableSeats);
  }

  private String getSeats(List<String> availableSeats) {
    StringBuilder availableSeat = new StringBuilder();
    if (availableSeat.equals("")) {
      return "";
    }
    for (String seat : availableSeats) {
      availableSeat.append(seat + ",");
    }
    availableSeat.deleteCharAt(availableSeat.length() - 1);
    return availableSeat.toString();
  }
}
