package com.tim9.accommodationservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.accommodationservice.dtos.PriceDTO;
import com.tim9.accommodationservice.models.Price;
import com.tim9.accommodationservice.repository.PriceRepository;
import com.tim9.accommodationservice.utils.dtoConverters.DTOPriceConverter;

@Service
public class PriceService {
	
	@Autowired
	PriceRepository priceRepository;
	
	@Autowired
	DTOPriceConverter priceConverter;
	
	

	public List<PriceDTO> findAll() {
		
		Optional< List<Price> > prices = Optional.of( priceRepository.findAll() );
		
		ArrayList < PriceDTO > dtoPrices = new ArrayList<PriceDTO>();
		
		if ( prices.isPresent() ) {
			
			for ( Price price : prices.get() ) {
				
				dtoPrices.add(priceConverter.convertToDTO(price));
				
			}
			
			return dtoPrices;
			
		}
			
		return Collections.emptyList();

		
	}

	public PriceDTO findById(Long id) {
		
		Optional< Price > price = priceRepository.findById(id);
		
		
		if ( price.isPresent() ) {
			
			return priceConverter.convertToDTO(price.get());
		
		}
		else {
			
			return new PriceDTO();
			
		}
		
	}

	public PriceDTO save(PriceDTO dto) {
		
		dto.setPriceId(-1l);
			
		Price price = priceRepository.save(priceConverter.convertFromDTO(dto));
		
		dto.setPriceId(price.getPriceId());
		
		return dto;

	}

	public PriceDTO update(Long id, PriceDTO priceDTO) {
		
		Optional< Price > priceForChange = priceRepository.findById(id);
		
		if( priceForChange.isPresent() && priceDTO!=null ) {
			
			
			priceForChange.get().setAmount(priceDTO.getAmount());
			priceForChange.get().setDateFrom(priceDTO.getDateFrom());
			priceForChange.get().setDateTo(priceDTO.getDateTo());
	
			priceRepository.save(priceForChange.get());
			
			priceDTO.setPriceId(priceForChange.get().getPriceId());
			
			
			return priceDTO;
		
		}
		
		return new PriceDTO();
	}

	public PriceDTO delete(Long id) {
		
		Optional< Price > priceToDelete = priceRepository.findById(id);
		
		if( priceToDelete.isPresent() ) {
			
			priceRepository.deleteById(id);
			
			return priceConverter.convertToDTO(priceToDelete.get());
		
		}
		
		return new PriceDTO();
		
	}

}
