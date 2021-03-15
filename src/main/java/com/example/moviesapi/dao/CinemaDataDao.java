package com.example.moviesapi.dao;

import com.example.moviesapi.models.Cinema;
import com.example.moviesapi.repo.CinemaDataLayer;
import org.springframework.stereotype.Service;

@Service
public class CinemaDataDao {
  private final CinemaDataLayer cinemaDataLayer;

  public CinemaDataDao(CinemaDataLayer cinemaDataLayer) {
    this.cinemaDataLayer = cinemaDataLayer;
  }

  public String getCinemaId(String cinemaName, String cityName) {
    return cinemaDataLayer.getCinemaIdByCinemaNameAndCityName(cinemaName, cityName);
  }
  public void insertData(Cinema cinema){
    cinemaDataLayer.saveAndFlush(cinema);
  }
}
