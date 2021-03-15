package com.example.moviesapi.models;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie_data")
@IdClass(value = MovieDataPK.class)
@Builder
public class MovieData {
  @Id
  public String cinemaId;
  @Id
  public String movieName;
  public Instant movieTiming;
  public int totalSeats;
  public String availableSeats;
}
