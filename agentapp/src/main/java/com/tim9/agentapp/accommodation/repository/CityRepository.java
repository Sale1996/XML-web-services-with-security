package com.tim9.agentapp.accommodation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.City;

public interface CityRepository extends JpaRepository<City,Long> {

	Optional<City> findByName(String name);

}
