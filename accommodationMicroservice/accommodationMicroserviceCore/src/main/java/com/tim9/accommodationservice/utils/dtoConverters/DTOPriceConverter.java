package com.tim9.accommodationservice.utils.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.accommodationservice.models.Price;
import com.tim9.accommodationservice.repository.PriceRepository;
import com.tim9.accommodationserviceclient.dtos.PriceDTO;


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
