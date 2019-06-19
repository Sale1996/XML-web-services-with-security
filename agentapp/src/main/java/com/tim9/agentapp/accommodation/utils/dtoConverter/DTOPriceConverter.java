package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.PriceDTO;
import com.tim9.agentapp.accommodation.model.PriceLocal;
import com.tim9.agentapp.accommodation.repository.PriceRepository;
import com.tim9.agentapp.accommodation.wsdl.Price;


@Component
public class DTOPriceConverter {

	@Autowired
	public PriceRepository priceRepository;
	
	@Autowired
	public DTOAccommodationUnitConverter accommodationUnitConverter;
	
	
	public PriceDTO convertToDTO (PriceLocal price) {
		
		PriceDTO dto = new PriceDTO();
		dto.setAccommodationUnit(accommodationUnitConverter.convertToDTO(price.getAccommodationUnit()));
		dto.setAmount(price.getAmount());
		dto.setLocalPriceId(price.getLocalPriceId());
		dto.setPriceId(price.getLocalPriceId());
		dto.setDateFrom(price.getDateFrom());
		dto.setDateTo(price.getDateTo());
		
		
		return dto;
		
	}
	
	public PriceDTO convertToDTOFromClient (Price price) {
		
		PriceDTO dto = new PriceDTO();
		dto.setAccommodationUnit(accommodationUnitConverter.convertToDTOFromClient(price.getAccommodationUnit()));
		dto.setAmount(price.getAmount());
		dto.setPriceId(price.getPriceId());
		dto.setPriceId(price.getPriceId());
		dto.setDateFrom(LocalDate.parse(price.getDateFrom()));
		dto.setDateTo(LocalDate.parse(price.getDateTo()));
		
		
		return dto;
		
	}
	
	public PriceLocal convertFromDTO( PriceDTO dto ) {
		
		Optional<PriceLocal> price = priceRepository.findById(dto.getPriceId());
		
		if(price.isPresent()) {
			
			return price.get();
			
		}
		
		PriceLocal newCandidate = new PriceLocal();
		
		newCandidate.setAccommodationUnit(accommodationUnitConverter.convertFromDTO(dto.getAccommodationUnit()));
		newCandidate.setAmount(dto.getAmount());
		newCandidate.setLocalPriceId(dto.getLocalPriceId());
		newCandidate.setPriceId(dto.getPriceId());
		newCandidate.setDateFrom(dto.getDateFrom());
		newCandidate.setDateTo(dto.getDateTo());

		
		return newCandidate;
		
	}
	
	
}
