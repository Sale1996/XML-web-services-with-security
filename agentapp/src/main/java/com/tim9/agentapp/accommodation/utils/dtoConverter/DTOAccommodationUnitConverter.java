package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.AccommodationUnitDTO;
import com.tim9.agentapp.accommodation.dto.ExtraFieldDTO;
import com.tim9.agentapp.accommodation.dto.PriceDTO;
import com.tim9.agentapp.accommodation.model.AccommodationUnit;
import com.tim9.agentapp.accommodation.model.ExtraField;
import com.tim9.agentapp.accommodation.model.Price;
import com.tim9.agentapp.accommodation.repository.AccommodationUnitRepository;

@Component
public class DTOAccommodationUnitConverter {

	@Autowired
	public AccommodationUnitRepository accommodationUnitRepository;
	
	
	@Autowired
	public DTOAccommodationConverter accommodationConverter;
	@Autowired
	public DTOTypeConverter typeConverter;
	@Autowired
	public DTOCategoryConverter categoryConverter;
	@Autowired
	public DTOExtraFieldConverter extraFieldConverter;
	@Autowired
	public DTOPriceConverter priceConverter;
	
	
	
	
	
	public AccommodationUnitDTO convertToDTO (AccommodationUnit accommodationUnit) {
		
		AccommodationUnitDTO dto = new AccommodationUnitDTO();
		
		dto.setLocalAccommodationUnitId(accommodationUnit.getLocalAccommodationUnitId());
		dto.setAccommodationUnitId(accommodationUnit.getAccommodationUnitId());
		dto.setAccommodation(accommodationConverter.convertToDTO(accommodationUnit.getAccommodation()));
		dto.setCategory(categoryConverter.convertToDTO(accommodationUnit.getCategory()));
		dto.setNumberOfPeople(accommodationUnit.getNumberOfPeople());
		dto.setType(typeConverter.convertToDTO(accommodationUnit.getType()));
		
		for(ExtraField extraField : accommodationUnit.getExtraField()) {
			dto.getExtraField().add(extraFieldConverter.convertToDTO(extraField));
		}

		for(Price price : accommodationUnit.getPrice()) {
			dto.getPrice().add(priceConverter.convertToDTO(price));
		}
		

		
		return dto;
		
	}
	
	public AccommodationUnit convertFromDTO( AccommodationUnitDTO dto ) {
		
		Optional<AccommodationUnit> accommodationUnit = accommodationUnitRepository.findById(dto.getAccommodationUnitId());
		
		if(accommodationUnit.isPresent()) {
			
			return accommodationUnit.get();
			
		}
		
		AccommodationUnit newBean = new AccommodationUnit();
		
		newBean.setLocalAccommodationUnitId(dto.getLocalAccommodationUnitId());
		newBean.setAccommodationUnitId(dto.getAccommodationUnitId());
		newBean.setAccommodation(accommodationConverter.convertFromDTO(dto.getAccommodation()));
		newBean.setCategory(categoryConverter.convertFromDTO(dto.getCategory()));
		newBean.setNumberOfPeople(dto.getNumberOfPeople());
		newBean.setType(typeConverter.convertFromDTO(dto.getType()));
		
		for(ExtraFieldDTO extraField : dto.getExtraField()) {
			newBean.getExtraField().add(extraFieldConverter.convertFromDTO(extraField));
		}

		for(PriceDTO price : dto.getPrice()) {
			newBean.getPrice().add(priceConverter.convertFromDTO(price));
		}

		
		return newBean;
		
	}
	
	
}
