package com.example.moviesapi.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "geo_data")
@IdClass(value = GeoData.class)
public class GeoData implements Serializable {
  @Id
  public String state;
  @Id
  public String city;
  public String cinemaId;
}
