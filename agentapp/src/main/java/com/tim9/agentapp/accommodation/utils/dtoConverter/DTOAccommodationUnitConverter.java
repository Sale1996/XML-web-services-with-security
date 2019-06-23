package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.AccommodationUnitDTO;
import com.tim9.agentapp.accommodation.dto.ExtraFieldDTO;
import com.tim9.agentapp.accommodation.model.AccommodationUnitLocal;
import com.tim9.agentapp.accommodation.model.ExtraFieldLocal;
import com.tim9.agentapp.accommodation.repository.AccommodationUnitRepository;
import com.tim9.agentapp.accommodation.wsdl.AccommodationUnit;

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
	
	
	
	
	
	public AccommodationUnitDTO convertToDTO (AccommodationUnitLocal accommodationUnit) {
		
		AccommodationUnitDTO dto = new AccommodationUnitDTO();
		
		dto.setLocalAccommodationUnitId(accommodationUnit.getLocalAccommodationUnitId());
		dto.setAccommodationUnitId(accommodationUnit.getAccommodationUnitId());
		dto.setAccommodation(accommodationConverter.convertToDTO(accommodationUnit.getAccommodation()));
		dto.setCategory(categoryConverter.convertToDTO(accommodationUnit.getCategory()));
		dto.setNumberOfPeople(accommodationUnit.getNumberOfPeople());
		dto.setType(typeConverter.convertToDTO(accommodationUnit.getType()));
		
		for(ExtraFieldLocal extraField : accommodationUnit.getExtraField()) {
			dto.getExtraField().add(extraFieldConverter.convertToDTO(extraField));
		}



		
		return dto;
		
	}
	
	public AccommodationUnitDTO convertToDTOFromClient (AccommodationUnit accommodationUnit) {
		
		AccommodationUnitDTO dto = new AccommodationUnitDTO();
		
		dto.setAccommodationUnitId(accommodationUnit.getAccommodationUnitId());
		dto.setAccommodationUnitId(accommodationUnit.getAccommodationUnitId());
		dto.setAccommodation(accommodationConverter.convertToDTOFromClient(accommodationUnit.getAccommodation()));
		dto.setCategory(categoryConverter.convertToDTOFromClient(accommodationUnit.getCategory()));
		dto.setNumberOfPeople(accommodationUnit.getNumberOfPeople());
		dto.setType(typeConverter.convertToDTOFromClient(accommodationUnit.getType()));
		
//		for(ExtraFieldLocal extraField : accommodationUnit.getExtraField()) {
//			dto.getExtraField().add(extraFieldConverter.convertToDTO(extraField));
//		}



		
		return dto;
		
	}
	
	public AccommodationUnitLocal convertFromDTO( AccommodationUnitDTO dto ) {
		
		Optional<AccommodationUnitLocal> accommodationUnit = accommodationUnitRepository.findById(dto.getAccommodationUnitId());
		
		if(accommodationUnit.isPresent()) {
			
			return accommodationUnit.get();
			
		}
		
		AccommodationUnitLocal newBean = new AccommodationUnitLocal();
		
		newBean.setLocalAccommodationUnitId(dto.getLocalAccommodationUnitId());
		newBean.setAccommodationUnitId(dto.getAccommodationUnitId());
		newBean.setAccommodation(accommodationConverter.convertFromDTO(dto.getAccommodation()));
		newBean.setCategory(categoryConverter.convertFromDTO(dto.getCategory()));
		newBean.setNumberOfPeople(dto.getNumberOfPeople());
		newBean.setType(typeConverter.convertFromDTO(dto.getType()));
		
		for(ExtraFieldDTO extraField : dto.getExtraField()) {
			newBean.getExtraField().add(extraFieldConverter.convertFromDTO(extraField));
		}



		
		return newBean;
		
	}
	
	public AccommodationUnit convertFromDTOToWsdl( AccommodationUnitDTO dto ) {
		
		AccommodationUnit newBean = new AccommodationUnit();
		
		newBean.setAccommodationUnitId(dto.getAccommodationUnitId());
		newBean.setAccommodation(accommodationConverter.convertFromDTOToWsdl(dto.getAccommodation()));
		newBean.setCategory(categoryConverter.convertFromDTOToWsdl(dto.getCategory()));
		newBean.setNumberOfPeople(dto.getNumberOfPeople());
		newBean.setType(typeConverter.convertFromDTOToWsdl(dto.getType()));

		return newBean;
		
	}
	
	
}
