package com.example.moviesapi.controller;

import com.example.moviesapi.models.BookedSeats;
import com.example.moviesapi.models.MoviesModel;
import com.example.moviesapi.models.RegisterCinema;
import com.example.moviesapi.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MoviesController {
  @Autowired
  private MoviesService moviesService;

  @GetMapping("movies")
  public ResponseEntity<?> getMovies() {
    return ResponseEntity.ok(moviesService.getMovies());
  }

  @PostMapping("add-movies")
  public ResponseEntity<?> insertMovies(@RequestBody MoviesModel moviesModel) {
    return ResponseEntity.ok(moviesService.insertMovie(moviesModel));
  }

  @PostMapping("register-cinema")
  public ResponseEntity<?> registerCinema(@RequestBody RegisterCinema registerCinema) {
    moviesService.registerCinema(registerCinema);
    return ResponseEntity.ok("Cinema Registered");
  }

  @PutMapping("book-tickets")
  public ResponseEntity<?> bookTicket(@RequestBody BookedSeats bookedSeats) {
    return ResponseEntity.ok(moviesService.bookTicket(bookedSeats));
  }

}
