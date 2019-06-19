package com.tim9.accommodationservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.models.Accommodation;
import com.tim9.accommodationservice.models.AccommodationUnit;
import com.tim9.accommodationservice.models.Category;
import com.tim9.accommodationservice.models.ExtraField;
import com.tim9.accommodationservice.models.Type;
import com.tim9.accommodationservice.repository.AccommodationRepository;
import com.tim9.accommodationservice.repository.AccommodationUnitRepository;
import com.tim9.accommodationservice.repository.CategoryRepository;
import com.tim9.accommodationservice.repository.ExtraFieldRepository;
import com.tim9.accommodationservice.repository.TypeRepository;
import com.tim9.accommodationservice.utils.DatasFromReservationMicroservice;
import com.tim9.accommodationservice.utils.dtoConverters.DTOAccommodationConverter;
import com.tim9.accommodationservice.utils.dtoConverters.DTOAccommodationUnitConverter;
import com.tim9.accommodationservice.utils.dtoConverters.DTOCategoryConverter;
import com.tim9.accommodationservice.utils.dtoConverters.DTOTypeConverter;
import com.tim9.accommodationserviceclient.dtos.AccommodationUnitDTO;
import com.tim9.accommodationserviceclient.dtos.SearchDTO;

@Service
public class AccommodationUnitService {
	
	
	AccommodationUnitRepository accommodationUnitRepository;
	AccommodationRepository accommodationRepository;
	TypeRepository typeRepository;
	CategoryRepository categoryRepository;
	ExtraFieldRepository extraFieldRepository;
	
	
	DTOAccommodationUnitConverter accommodationUnitConverter;
	DTOCategoryConverter categoryConverter;
	DTOTypeConverter typeConverter;
	DTOAccommodationConverter accommodationConverter;
	
	DatasFromReservationMicroservice reservationMicroservice;
	
	public AccommodationUnitService(DatasFromReservationMicroservice reservationMicroservice, AccommodationUnitRepository unitRepository, DTOAccommodationUnitConverter unitConverter,
			AccommodationRepository accommodationRepository, TypeRepository typeRepository, CategoryRepository categoryRepository,
			DTOCategoryConverter categoryConverter, DTOTypeConverter typeConverter, DTOAccommodationConverter accommodationConverter,
			ExtraFieldRepository extraFieldRepository) {
		
		this.reservationMicroservice = reservationMicroservice;
		this.accommodationUnitRepository = unitRepository;
		this.accommodationUnitConverter = unitConverter;
		this.accommodationRepository = accommodationRepository;
		this.typeRepository = typeRepository;
		this.categoryRepository = categoryRepository;
		this.categoryConverter = categoryConverter;
		this.typeConverter = typeConverter;
		this.accommodationConverter = accommodationConverter;
		this.extraFieldRepository = extraFieldRepository;
		
		
	}

	
	
	public List<AccommodationUnitDTO> findAll() {
		
		Optional< List<AccommodationUnit> > accommodationUnits = Optional.of ( accommodationUnitRepository.findAll() );
		
		ArrayList < AccommodationUnitDTO > dtoAccommodationUnits = new ArrayList< AccommodationUnitDTO >();
		
		if ( accommodationUnits.isPresent() ) {
			
			for ( AccommodationUnit units : accommodationUnits.get() ) {
				
				dtoAccommodationUnits.add(accommodationUnitConverter.convertToDTO(units));
				
			}
			
			return dtoAccommodationUnits;
			
		}
			
		return Collections.emptyList();

		
	}

	public AccommodationUnitDTO findById(Long id) {
		
		Optional< AccommodationUnit > accommodationUnit = accommodationUnitRepository.findById(id);
		
		
		if ( accommodationUnit.isPresent() ) {
			
			return accommodationUnitConverter.convertToDTO(accommodationUnit.get());
		
		}
		else {
			
			return new AccommodationUnitDTO();
			
		}
		
	}

	public AccommodationUnitDTO save(AccommodationUnitDTO dto) {
			
		dto.setAccommodationUnitId(-1l);
		
		if(!validateAccommodationUnit(dto)) {
			return new AccommodationUnitDTO();
		}
		
		AccommodationUnit AccommodationUnit = accommodationUnitConverter.convertFromDTO(dto);
		AccommodationUnit.setLastUpdated(LocalDateTime.now());
		AccommodationUnit = accommodationUnitRepository.save(AccommodationUnit);
		
		dto.setAccommodationUnitId(AccommodationUnit.getAccommodationUnitId());
		
		return dto;
	
	}

	public AccommodationUnitDTO update(Long id, AccommodationUnitDTO accommodationUnitDTO) {
		
		Optional<AccommodationUnit> accommodationUnitForChange = accommodationUnitRepository.findById(id);
		
		if( accommodationUnitForChange.isPresent() && accommodationUnitDTO!=null ) {
			
			if(!validateAccommodationUnit(accommodationUnitDTO)) {
				return new AccommodationUnitDTO();
			}
			
			accommodationUnitForChange.get().setNumberOfPeople(accommodationUnitDTO.getNumberOfPeople());
			accommodationUnitForChange.get().setLastUpdated(LocalDateTime.now());
			accommodationUnitForChange.get().setAccommodation(accommodationConverter.convertFromDTO(accommodationUnitDTO.getAccomodation()));
			accommodationUnitForChange.get().setCategory(categoryConverter.convertFromDTO(accommodationUnitDTO.getCategory()));
			accommodationUnitForChange.get().setType(typeConverter.convertFromDTO(accommodationUnitDTO.getType()));
	
			
			accommodationUnitRepository.save(accommodationUnitForChange.get());
			
			accommodationUnitDTO.setAccommodationUnitId(accommodationUnitForChange.get().getAccommodationUnitId());
			
			
			return accommodationUnitDTO;
		
		}
		
		return new AccommodationUnitDTO();
	}

	public AccommodationUnitDTO delete(Long id) {
		
		Optional<AccommodationUnit> accommodationUnitToDelete = accommodationUnitRepository.findById(id);
		
		if( accommodationUnitToDelete.isPresent() ) {
			
			accommodationUnitRepository.deleteById(id);
			
			return accommodationUnitConverter.convertToDTO(accommodationUnitToDelete.get());
		
		}
		
		return new AccommodationUnitDTO();
		
	}

	public List<AccommodationUnit> findAllByAccommodationAgentId(long id) {
		
		Optional< List<AccommodationUnit> > accommodationUnits = Optional.of ( accommodationUnitRepository.findAllByAccommodationAgentId(id) );
		
		
		if ( accommodationUnits.isPresent() ) {
			
			return accommodationUnits.get();
			
		}
			
		return Collections.emptyList();

		
	}
	
	
	public AccommodationUnit addExtraField(Long accommodationUnitId, Long extraFieldId) {
		
		
		Optional<AccommodationUnit> unit = accommodationUnitRepository.findById(accommodationUnitId);
		Optional<ExtraField> extraField = extraFieldRepository.findById(extraFieldId);
		if(!unit.isPresent() || !extraField.isPresent()) {
			
			return new AccommodationUnit();
		
		}
		
		//if unit already has same extra field, we wont add it twice
		for(ExtraField field : unit.get().getExtraFields()) {
			if(field.getExtraFieldId() == extraField.get().getExtraFieldId()) {
				return new AccommodationUnit();
			}
		}
		
		
		unit.get().getExtraFields().add(extraField.get());
		accommodationUnitRepository.save(unit.get());
		
		
		
		return unit.get();
	}
	
	
	public AccommodationUnit removeExtraField(Long accommodationUnitId, Long extraFieldId) {
		
		
		Optional<AccommodationUnit> unit = accommodationUnitRepository.findById(accommodationUnitId);
		Optional<ExtraField> extraField = extraFieldRepository.findById(extraFieldId);
		
		if(!unit.isPresent() || !extraField.isPresent()) {
			
			return new AccommodationUnit();
		
		}
		
		for(ExtraField field : unit.get().getExtraFields()) {
			if(field.getExtraFieldId() == extraField.get().getExtraFieldId()) {
				unit.get().getExtraFields().remove(field);
				break;
			}
		}
		
		accommodationUnitRepository.save(unit.get());
		
		
		
		return unit.get();
	}
	
	
	private boolean validateAccommodationUnit(AccommodationUnitDTO unit) {
		
		boolean isValid = true;
		
		if(unit.getNumberOfPeople() < 1) {
			isValid = false;
		}
		
		Optional<Type> type = typeRepository.findById(unit.getType().getTypeId());
		Optional<Category> category = categoryRepository.findById(unit.getCategory().getCategoryId());
		Optional<Accommodation> accommodation = accommodationRepository.findById(unit.getAccomodation().getAccommodationId());
		
		if(!type.isPresent() || !category.isPresent() || !accommodation.isPresent()) {
			isValid = false;
		}
		
	
		
		return isValid;
				
	}

	public List<AccommodationUnitDTO> findAllAccommodationUnitsByAccommodationId(Long accommodationId, SearchDTO search) {
		
		// ovde se dobavljaju sve jedinice navedenih akomodacija koje SU ZAUZETE u navedenom periodu
		String dateFrom = search.getDateFrom();
		String dateTo = search.getDateTo();
		List<Long> accid = new ArrayList<>();
		accid.add(accommodationId);
		List<Long> accommodationUnits = reservationMicroservice.getAccommodationUnitIds(accid, dateFrom, dateTo);
		
		accommodationUnits.add(-1l);
		
		search.getExtraFields().add(-1l);
		
		// sad treba iz  baze izvuci sve akomodacije cije slobodne jedinice imaju potreban kapacitet
		Optional<List<AccommodationUnit>> accommodations = accommodationUnitRepository.searchAccommodationUnits(accid, accommodationUnits, search.getType(), search.getCategory(), search.getExtraFields(),search.getExtraFields().size()-1);
		ArrayList < AccommodationUnitDTO > dtoAccommodations = new ArrayList< AccommodationUnitDTO >();
		if ( accommodations.isPresent() ) {
			for ( AccommodationUnit  candidate : accommodations.get() ) {
				dtoAccommodations.add(accommodationUnitConverter.convertToDTO(candidate));
			}
			return dtoAccommodations;
		}
		return Collections.emptyList();
	}
}
