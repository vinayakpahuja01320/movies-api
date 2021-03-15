package com.example.moviesapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCinema {
  public String state;
  public String city;
  public String cinemaName;

}
