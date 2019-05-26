package com.tim9.accommodationservice.utils.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.accommodationservice.models.AccommodationUnit;
import com.tim9.accommodationservice.models.ExtraField;
import com.tim9.accommodationservice.models.Price;
import com.tim9.accommodationservice.repository.AccommodationUnitRepository;
import com.tim9.accommodationserviceclient.dtos.AccommodationUnitDTO;
import com.tim9.accommodationserviceclient.dtos.ExtraFieldDTO;
import com.tim9.accommodationserviceclient.dtos.PriceDTO;

@Component
public class DTOAccommodationUnitConverter {

	@Autowired
	public AccommodationUnitRepository accommodationUnitRepository;
	
	

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
		
		dto.setAccommodationUnitId(accommodationUnit.getAccommodationUnitId());
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
		
		newBean.setAccommodationUnitId(dto.getAccommodationUnitId());
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
