package com.example.moviesapi.models;

import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MoviesModel {
  public String movieName;
  public String cityName;
  public String cinemaName;
  public Instant movieTiming;
  public int totalSeats;
  public List<String> availableSeats;
}
