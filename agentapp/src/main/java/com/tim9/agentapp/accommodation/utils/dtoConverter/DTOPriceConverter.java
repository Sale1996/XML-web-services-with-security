package com.tim9.agentapp.accommodation.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.accommodation.dto.PriceDTO;
import com.tim9.agentapp.accommodation.model.Price;
import com.tim9.agentapp.accommodation.repository.PriceRepository;


@Component
public class DTOPriceConverter {

	@Autowired
	public PriceRepository priceRepository;
	
	
	
	
	public PriceDTO convertToDTO (Price price) {
		
		PriceDTO dto = new PriceDTO();
		
		dto.setAmount(price.getAmount());
		dto.setPriceId(price.getPriceId());
		dto.setDateFrom(price.getDateFrom());
		dto.setDateTo(price.getDateTo());
		
		
		return dto;
		
	}
	
	public Price convertFromDTO( PriceDTO dto ) {
		
		Optional<Price> price = priceRepository.findById(dto.getPriceId());
		
		if(price.isPresent()) {
			
			return price.get();
			
		}
		
		Price newCandidate = new Price();
		
		newCandidate.setAmount(dto.getAmount());
		newCandidate.setPriceId(dto.getPriceId());
		newCandidate.setDateFrom(dto.getDateFrom());
		newCandidate.setDateTo(dto.getDateTo());

		
		return newCandidate;
		
	}
	
	
}
