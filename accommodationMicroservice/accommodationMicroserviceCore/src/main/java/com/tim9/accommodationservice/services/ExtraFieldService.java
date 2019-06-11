package com.tim9.accommodationservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.models.ExtraField;
import com.tim9.accommodationservice.repository.ExtraFieldRepository;
import com.tim9.accommodationservice.utils.dtoConverters.DTOExtraFieldConverter;
import com.tim9.accommodationserviceclient.dtos.ExtraFieldDTO;

@Service
public class ExtraFieldService {
	
	
	ExtraFieldRepository extraFieldRepository;
	
	
	DTOExtraFieldConverter extraFieldConverter;
	
	
	public ExtraFieldService(ExtraFieldRepository extraFieldRepository, DTOExtraFieldConverter extraFieldConverter) {
		this.extraFieldConverter = extraFieldConverter;
		this.extraFieldRepository = extraFieldRepository;
	}
	
	

	public List<ExtraFieldDTO> findAll() {
		
		Optional< List<ExtraField> > extraFields = Optional.of( extraFieldRepository.findAll() );
		
		ArrayList < ExtraFieldDTO > dtoExtraFields = new ArrayList<ExtraFieldDTO>();
		
		if ( extraFields.isPresent() ) {
			
			for ( ExtraField extraField : extraFields.get() ) {
				
				dtoExtraFields.add(extraFieldConverter.convertToDTO(extraField));
				
			}
			
			return dtoExtraFields;
			
		}
			
		return Collections.emptyList();

		
	}

	public ExtraFieldDTO findById(Long id) {
		
		Optional< ExtraField > extraField = extraFieldRepository.findById(id);
		
		
		if ( extraField.isPresent() ) {
			
			return extraFieldConverter.convertToDTO(extraField.get());
		
		}
		else {
			
			return new ExtraFieldDTO();
			
		}
		
	}

	public ExtraFieldDTO save(ExtraFieldDTO dto) {
		
		//checking if there is already ExtraField with the same name
	
		Optional< ExtraField > foundExtraField = extraFieldRepository.findByExtraFieldName(dto.getExtraFieldName());
		//extraPrice shouldn't be lower than zero
		if( foundExtraField.isPresent() || dto.getExtraPrice()<0 ) {
			
			return new ExtraFieldDTO();
		
		}
		

		
		dto.setExtraFieldId(-1l);
			
		ExtraField ExtraField = extraFieldConverter.convertFromDTO(dto);
		ExtraField.setLastUpdated(LocalDateTime.now());
		ExtraField = extraFieldRepository.save(ExtraField);
		
		dto.setExtraFieldId(ExtraField.getExtraFieldId());
		
		return dto;

	}

	public ExtraFieldDTO update(Long id, ExtraFieldDTO updatedExtraField) {
		
		Optional< ExtraField > extraFieldForChange = extraFieldRepository.findById(id);
		
		if( extraFieldForChange.isPresent() && updatedExtraField!=null ) {
			
			//checking if there is already ExtraField with the same name but not same id
			//extraPrice shouldn't be lower than zero

			Optional<ExtraField> foundExtraField = extraFieldRepository.findByExtraFieldName(updatedExtraField.getExtraFieldName());
			
			if(updatedExtraField.getExtraPrice()<0 || (foundExtraField.isPresent() && foundExtraField.get().getExtraFieldId() != extraFieldForChange.get().getExtraFieldId() )) {
				
				return new ExtraFieldDTO();
			
			}
			
			extraFieldForChange.get().setExtraFieldName(updatedExtraField.getExtraFieldName());
			extraFieldForChange.get().setExtraPrice(updatedExtraField.getExtraPrice());
			extraFieldForChange.get().setOptional(updatedExtraField.isOptional());
			extraFieldForChange.get().setLastUpdated(LocalDateTime.now());
	
			extraFieldRepository.save(extraFieldForChange.get());
			
			updatedExtraField.setExtraFieldId(extraFieldForChange.get().getExtraFieldId());
			
			
			return updatedExtraField;
		
		}
		
		return new ExtraFieldDTO();
	}

	public ExtraFieldDTO delete(Long id) {
		
		Optional< ExtraField > extraFieldToDelete = extraFieldRepository.findById(id);
		
		if( extraFieldToDelete.isPresent() ) {
			
			extraFieldRepository.deleteById(id);
			
			return extraFieldConverter.convertToDTO(extraFieldToDelete.get());
		
		}
		
		return new ExtraFieldDTO();
		
	}

	public List<ExtraField> findAllSoap() {
		
		Optional< List<ExtraField> > extraFields = Optional.of( extraFieldRepository.findAll() );
				
		if ( extraFields.isPresent() ) {
			
			return extraFields.get();
		}
			
		return Collections.emptyList();

		
	}

}
