package com.example.moviesapi.models;

import java.sql.Timestamp;
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
public class MoviesUi {
  public Instant movieTiming;
  public int totalSeats;
  public List<String> availableSeats;
}
