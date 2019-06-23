package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.AccommodationUnitDTO;
import com.tim9.agentapp.accommodation.model.AccommodationUnitLocal;
import com.tim9.agentapp.accommodation.model.ExtraFieldLocal;
import com.tim9.agentapp.accommodation.repository.AccommodationUnitRepository;
import com.tim9.agentapp.accommodation.repository.ExtraFieldRepository;
import com.tim9.agentapp.accommodation.soapclient.AccommodationUnitClient;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOAccommodationUnitConverter;
import com.tim9.agentapp.accommodation.wsdl.AccommodationUnit;
import com.tim9.agentapp.accommodation.wsdl.AddExtraFieldToUnitResponse;
import com.tim9.agentapp.accommodation.wsdl.CreateAccommodationUnitResponse;
import com.tim9.agentapp.accommodation.wsdl.DeleteAccommodationUnitResponse;
import com.tim9.agentapp.accommodation.wsdl.GetAccommodationUnitsResponse;
import com.tim9.agentapp.accommodation.wsdl.UpdateAccommodationUnitResponse;

@Service
public class AccommodationUnitService {
	
	@Autowired
	AccommodationUnitRepository accommodationUnitRepository;
	
	@Autowired
	AccommodationUnitClient accommodationUnitClient;
	
	@Autowired
	ExtraFieldRepository extraFieldRepository;
	
	@Autowired
	DTOAccommodationUnitConverter accommodationUnitConverter;
	
	@Autowired
	AccommodationService accommodationService;

	
	
	public List<AccommodationUnitDTO> findAll(Long id) {
		
//		Optional< List<AccommodationUnitLocal> > accommodationUnits = Optional.of ( accommodationUnitRepository.findAll() );
//		
//		ArrayList < AccommodationUnitDTO > dtoAccommodationUnits = new ArrayList< AccommodationUnitDTO >();
//		
//		if ( accommodationUnits.isPresent() ) {
//			
//			for ( AccommodationUnitLocal units : accommodationUnits.get() ) {
//				
//				dtoAccommodationUnits.add(accommodationUnitConverter.convertToDTO(units));
//				
//			}
//			
//			return dtoAccommodationUnits;
//			
//		}
//			
//		return Collections.emptyList();
		
		GetAccommodationUnitsResponse response =  accommodationUnitClient.getAccommodationUnits(id);
		List<AccommodationUnitDTO> dtoTypes = new ArrayList<AccommodationUnitDTO>();
		
		if(!response.getAccommodationUnits().isEmpty()) {
			
			for ( AccommodationUnit type : response.getAccommodationUnits() ) {
				dtoTypes.add(accommodationUnitConverter.convertToDTOFromClient(type));	
			}
			
			return dtoTypes;
		}
		
		return Collections.emptyList();

		
	}

	public AccommodationUnitDTO findById(Long id) {
		
		Optional< AccommodationUnitLocal > accommodationUnit = accommodationUnitRepository.findById(id);
		
		
		if ( accommodationUnit.isPresent() ) {
			
			return accommodationUnitConverter.convertToDTO(accommodationUnit.get());
		
		}
		else {
			
			return new AccommodationUnitDTO();
			
		}
		
	}

	public AccommodationUnitDTO save(AccommodationUnitDTO dto) {

//		dto.setAccommodationUnitId(-1l);
//		
//		AccommodationUnitLocal AccommodationUnit = accommodationUnitConverter.convertFromDTO(dto);
//		AccommodationUnit = accommodationUnitRepository.save(AccommodationUnit);
//		
//		dto.setAccommodationUnitId(AccommodationUnit.getAccommodationUnitId());
//		
//		return dto;
		
		dto.setAccommodationUnitId(-1l);
		dto.setAccommodation(accommodationService.findById("token", dto.getAccommodation().getAccommodationId()));
		
		AccommodationUnit accommodationUnit = accommodationUnitConverter.convertFromDTOToWsdl(dto);
		CreateAccommodationUnitResponse response =  accommodationUnitClient.createAccommodationUnit(accommodationUnit);
		
		if(response.getAccommodationUnit().getAccommodationUnitId() != null) {

			return accommodationUnitConverter.convertToDTOFromClient(response.getAccommodationUnit());
		}

		return new AccommodationUnitDTO();
	
	}

	public AccommodationUnitDTO update(Long id, AccommodationUnitDTO accommodationUnitDTO) {
		
//		Optional<AccommodationUnitLocal> accommodationUnitForChange = accommodationUnitRepository.findById(id);
//		
//		if( accommodationUnitForChange.isPresent() && accommodationUnitDTO!=null ) {
//													
//			accommodationUnitForChange.get().setNumberOfPeople(accommodationUnitDTO.getNumberOfPeople());
//			//accommodationUnitForChange.get().setCategory();
//			//accommodationUnitForChange.get().setType(value); to be implemented...
//			//i jos ima lista koje treba prekopirati ukoliko trbea
//
//	
//			accommodationUnitRepository.save(accommodationUnitForChange.get());
//			
//			accommodationUnitDTO.setAccommodationUnitId(accommodationUnitForChange.get().getAccommodationUnitId());
//			
//			
//			return accommodationUnitDTO;
//		
//		}
//		
//		return new AccommodationUnitDTO();
		
		
		
//		dto.setAccommodation(accommodationService.findById("token", dto.getAccommodation().getAccommodationId()));
		
		AccommodationUnit accommodationUnit = accommodationUnitConverter.convertFromDTOToWsdl(accommodationUnitDTO);
		UpdateAccommodationUnitResponse response =  accommodationUnitClient.updateAccommodationUnit(accommodationUnit);
		
		if(response.getAccommodationUnit().getAccommodationUnitId() != null) {

			return accommodationUnitConverter.convertToDTOFromClient(response.getAccommodationUnit());
		}

		return new AccommodationUnitDTO();
	}

	public AccommodationUnitDTO delete(Long id) {
		
//		Optional<AccommodationUnitLocal> accommodationUnitToDelete = accommodationUnitRepository.findById(id);
//		
//		if( accommodationUnitToDelete.isPresent() ) {
//			
//			accommodationUnitRepository.deleteById(id);
//			
//			return accommodationUnitConverter.convertToDTO(accommodationUnitToDelete.get());
//		
//		}
//		
//		return new AccommodationUnitDTO();
		
		DeleteAccommodationUnitResponse response =  accommodationUnitClient.deleteAccommodationUnit(id);
		
		if(response.getAccommodationUnit().getAccommodationUnitId() != null) {
			
			return accommodationUnitConverter.convertToDTOFromClient(response.getAccommodationUnit());
		}
		
		return new AccommodationUnitDTO();
		
	}
	
	
	public AccommodationUnitDTO addExtraField(Long accommodationUnitId, Long extraFieldId) {
		
		
//		Optional<AccommodationUnitLocal> unit = accommodationUnitRepository.findById(accommodationUnitId);
//		Optional<ExtraFieldLocal> extraField = extraFieldRepository.findById(extraFieldId);
//		
//		if(!unit.isPresent() || !extraField.isPresent()) {
//			
//			return new AccommodationUnitDTO();
//		
//		}
//		
//		//if unit already has same extra field, we wont add it twice
//		for(ExtraFieldLocal field : unit.get().getExtraField()) {
//			if(field.getExtraFieldId() == extraField.get().getExtraFieldId()) {
//				return new AccommodationUnitDTO();
//
//			}
//		}
//		
//		
//		unit.get().getExtraField().add(extraField.get());
//		accommodationUnitRepository.save(unit.get());
//		
//		
//		
//		return accommodationUnitConverter.convertToDTO(unit.get());
		
		AddExtraFieldToUnitResponse response = accommodationUnitClient.addExtraFieldToAccommodationUnit(accommodationUnitId, extraFieldId);
		
		if(response.getAccommodationUnit().getAccommodationUnitId() != null) {
			return accommodationUnitConverter.convertToDTOFromClient(response.getAccommodationUnit());
		}
		
		return new AccommodationUnitDTO();
	}
	
	
	public AccommodationUnitDTO removeExtraField(Long accommodationUnitId, Long extraFieldId) {
		
		
		Optional<AccommodationUnitLocal> unit = accommodationUnitRepository.findById(accommodationUnitId);
		Optional<ExtraFieldLocal> extraField = extraFieldRepository.findById(extraFieldId);
		
		if(!unit.isPresent() || !extraField.isPresent()) {
			
			return new AccommodationUnitDTO();
		
		}
		
		for(ExtraFieldLocal field : unit.get().getExtraField()) {
			if(field.getExtraFieldId() == extraField.get().getExtraFieldId()) {
				unit.get().getExtraField().remove(field);
				break;
			}
		}
		
		accommodationUnitRepository.save(unit.get());
		
		
		
		return accommodationUnitConverter.convertToDTO(unit.get());
	}

}
