package com.example.moviesapi.models;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies {
  public String state;
  public String city;
  public String cinemaName;
  public String movieName;
  public Instant movieTiming;
  public int totalSeats;
  public String availableSeats;
}
