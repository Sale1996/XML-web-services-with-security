package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.PriceDTO;
import com.tim9.agentapp.accommodation.model.Price;
import com.tim9.agentapp.accommodation.repository.PriceRepository;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOPriceConverter;

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
		
		//checking if dateFrom is before dateTo and price atribute is greater than zero
		
		if(dto.getDateFrom().isAfter(dto.getDateTo()) || dto.getAmount()<0) {
			
			return new PriceDTO();
			
		}
		
		dto.setPriceId(-1l);
			
		Price price = priceConverter.convertFromDTO(dto);
		price = priceRepository.save(price);
		
		dto.setPriceId(price.getPriceId());
		
		return dto;

	}

	public PriceDTO update(Long id, PriceDTO priceDTO) {
		
		Optional< Price > priceForChange = priceRepository.findById(id);
		//checking if dateFrom is before dateTo and price atribute is greater than zero
		
		if( priceForChange.isPresent() && priceDTO!=null) {
			
			//checking if dateFrom is before dateTo and price atribute is greater than zero
			
			if(priceDTO.getDateFrom().isAfter(priceDTO.getDateTo()) || priceDTO.getAmount()<0) {
				
				return new PriceDTO();
				
			}
			
			
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

	public List<PriceDTO> findAllByUnit(Long id) {
		
		Optional< List<Price> > prices = Optional.of( priceRepository.findAllByAccommodationUnitLocalAccommodationUnitId(id) );
		
		ArrayList < PriceDTO > dtoPrices = new ArrayList<PriceDTO>();
		
		if ( prices.isPresent() ) {
			
			for ( Price price : prices.get() ) {
				
				dtoPrices.add(priceConverter.convertToDTO(price));
				
			}
			
			return dtoPrices;
			
		}
			
		return Collections.emptyList();

		
	}

}
