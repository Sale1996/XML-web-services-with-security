package com.tim9.accommodationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim9.accommodationservice.models.Picture;

public interface PictureRepository extends JpaRepository<Picture,Long>{

	List<Picture> findAllByAccommodationAccommodationId(Long id);

}
