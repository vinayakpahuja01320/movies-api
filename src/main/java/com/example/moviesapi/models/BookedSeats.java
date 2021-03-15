package com.example.moviesapi.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookedSeats {
  public String cityName;
  public String cinemaName;
  public String movieName;
  public List<String> bookedSeats;
}
