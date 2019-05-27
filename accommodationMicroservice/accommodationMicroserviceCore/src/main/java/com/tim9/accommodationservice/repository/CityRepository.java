package com.tim9.accommodationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.accommodationservice.models.City;

public interface CityRepository extends JpaRepository<City,Long> {

	Optional<City> findByName(String name);

}
