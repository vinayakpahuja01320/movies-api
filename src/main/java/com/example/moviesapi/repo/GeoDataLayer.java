package com.example.moviesapi.repo;

import com.example.moviesapi.models.GeoData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoDataLayer extends JpaRepository<GeoData, GeoData> {
}
