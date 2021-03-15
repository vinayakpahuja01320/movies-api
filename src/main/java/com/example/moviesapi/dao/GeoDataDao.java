package com.example.moviesapi.dao;

import com.example.moviesapi.models.GeoData;
import com.example.moviesapi.repo.GeoDataLayer;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GeoDataDao {
  private final GeoDataLayer geoDataLayer;

  public GeoDataDao(GeoDataLayer geoDataLayer) {
    this.geoDataLayer = geoDataLayer;
  }

  public List<GeoData> getConstantData() {
    return geoDataLayer.findAll();
  }

  public void insertData(GeoData geoData) {
    geoDataLayer.saveAndFlush(geoData);
  }
}
