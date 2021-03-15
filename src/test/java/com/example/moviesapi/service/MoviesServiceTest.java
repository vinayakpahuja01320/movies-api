package com.example.moviesapi.service;

import com.example.moviesapi.dao.CinemaDataDao;
import com.example.moviesapi.dao.MoviesDataDao;
import com.example.moviesapi.models.BookedSeats;
import com.example.moviesapi.models.MovieData;
import com.example.moviesapi.models.MovieDataPK;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MoviesServiceTest {
  public static final String MOVIE_NAME = "movieName";
  public static final String CINEMA_ID = "cinemaId";
  public static final String CINEMA_NAME = "cinemaName";
  public static final String CITY_NAME = "cityName";
  @InjectMocks
  private MoviesService moviesService;
  @Mock
  private MoviesDataDao moviesDataDao;
  @Mock
  private CinemaDataDao cinemaDataDao;

  private MovieData getMovieData(String availableSeats, int totalSeats) {
    return MovieData.builder()
        .movieName(MOVIE_NAME)
        .totalSeats(totalSeats)
        .availableSeats(availableSeats)
        .cinemaId(CINEMA_ID)
        .build();
  }

  private BookedSeats getBookedTickets() {
    List<String> bookedSeats = new ArrayList<>();
    bookedSeats.add("c1");
    bookedSeats.add("c3");
    return BookedSeats.builder()
        .bookedSeats(bookedSeats)
        .cinemaName(CINEMA_NAME)
        .cityName(CITY_NAME)
        .movieName(MOVIE_NAME)
        .build();
  }

  private MovieDataPK getMovieDataPk() {
    return MovieDataPK.builder().movieName(MOVIE_NAME).cinemaId(CINEMA_ID).build();
  }

  @Before
  public void init() {
    Mockito.when(cinemaDataDao.getCinemaId(CINEMA_NAME, CITY_NAME)).thenReturn(CINEMA_ID);
    Mockito.when(moviesDataDao.getMoviesById(getMovieDataPk()))
        .thenReturn(getMovieData("c1,c2,c3", 3));

  }

  @Test
  public void testBookTicketWithPositiveCase() {
    String availableSeats = "c2";
    moviesService.bookTicket(getBookedTickets());
    Mockito.verify(moviesDataDao).addMovies(getMovieData(availableSeats, 1));
  }

  @Test
  public void testBookTicketsWithNoEntryInDB() {
    Mockito.when(moviesDataDao.getMoviesById(getMovieDataPk()))
        .thenReturn(null);
    moviesService.bookTicket(getBookedTickets());
    Mockito.verify(moviesDataDao, Mockito.never()).addMovies(getMovieData("c2", 1));
  }

  @Test
  public void testBookTicketsWithNoSeatAvailable() {
    String availableSeats = "c2";

    BookedSeats bookedSeats = BookedSeats.builder()
        .bookedSeats(Collections.singletonList("c3"))
        .cinemaName(CINEMA_NAME)
        .cityName(CITY_NAME)
        .movieName(MOVIE_NAME)
        .build();
    moviesService.bookTicket(bookedSeats);
    Mockito.verify(moviesDataDao,Mockito.never()).addMovies(getMovieData(availableSeats, 1));
  }
}