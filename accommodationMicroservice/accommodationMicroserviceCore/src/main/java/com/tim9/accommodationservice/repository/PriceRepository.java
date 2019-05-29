package com.tim9.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim9.accommodationservice.models.Price;


public interface PriceRepository extends JpaRepository<Price,Long> {

}