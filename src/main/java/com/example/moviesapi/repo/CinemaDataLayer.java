package com.example.moviesapi.repo;

import com.example.moviesapi.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CinemaDataLayer extends JpaRepository<Cinema, String> {
  @Query(value = "Select c.cinemaId from Cinema c where c.cinemaName=?1 and c.cityName=?2")
  String getCinemaIdByCinemaNameAndCityName(String cinemaName, String cityName);
}
