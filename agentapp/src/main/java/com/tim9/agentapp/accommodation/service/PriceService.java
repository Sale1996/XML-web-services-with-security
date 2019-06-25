package com.tim9.agentapp.accommodation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.dto.PriceDTO;
import com.tim9.agentapp.accommodation.model.PriceLocal;
import com.tim9.agentapp.accommodation.repository.PriceRepository;
import com.tim9.agentapp.accommodation.soapclient.PriceClient;
import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOPriceConverter;
import com.tim9.agentapp.accommodation.wsdl.CreatePriceResponse;
import com.tim9.agentapp.accommodation.wsdl.DeletePriceResponse;
import com.tim9.agentapp.accommodation.wsdl.GetPricesResponse;
import com.tim9.agentapp.accommodation.wsdl.Price;

@Service
public class PriceService {
	
	@Autowired
	PriceRepository priceRepository;
	
	@Autowired
	PriceClient priceClient;
	
	@Autowired
	DTOPriceConverter priceConverter;
	
	

	public List<PriceDTO> findAll() {
		
		Optional< List<PriceLocal> > prices = Optional.of( priceRepository.findAll() );
		
		ArrayList < PriceDTO > dtoPrices = new ArrayList<PriceDTO>();
		
		if ( prices.isPresent() ) {
			
			for ( PriceLocal price : prices.get() ) {
				
				dtoPrices.add(priceConverter.convertToDTO(price));
				
			}
			
			return dtoPrices;
			
		}
			
		return Collections.emptyList();

		
	}

	public PriceDTO findById(Long id) {
		
		Optional< PriceLocal > price = priceRepository.findById(id);
		
		
		if ( price.isPresent() ) {
			
			return priceConverter.convertToDTO(price.get());
		
		}
		else {
			
			return new PriceDTO();
			
		}
		
	}

	public PriceDTO save(PriceDTO dto) {
		
		//checking if dateFrom is before dateTo and price atribute is greater than zero
		
//		if(dto.getDateFrom().isAfter(dto.getDateTo()) || dto.getAmount()<0) {
//			
//			return new PriceDTO();
//			
//		}
//		
//		dto.setPriceId(-1l);
//		
//			
//		PriceLocal price = priceConverter.convertFromDTO(dto);
//		price.setLocalPriceId(-1l);
//		price.setPriceId(-1l);
//
//		price = priceRepository.save(price);
//		
//		
//		dto.setPriceId(price.getPriceId());
//		
//		return dto;
		if(dto.getDateFrom().isAfter(dto.getDateTo()) || dto.getAmount()<0) {
		
			return new PriceDTO();
		
		}
		dto.setPriceId(-1l);
		
		CreatePriceResponse response = priceClient.createPrice(priceConverter.convertFromDTOToWsdl(dto));
		
		if(response.getPrice().getPriceId() != null) {
			return priceConverter.convertToDTOFromClient(response.getPrice());
		}
		
		return new PriceDTO();

	}

	public PriceDTO update(Long id, PriceDTO priceDTO) {
		
		Optional< PriceLocal > priceForChange = priceRepository.findById(id);
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
		
//		Optional< PriceLocal > priceToDelete = priceRepository.findById(id);
//		
//		if( priceToDelete.isPresent() ) {
//			
//			priceRepository.deleteById(id);
//			
//			return priceConverter.convertToDTO(priceToDelete.get());
//		
//		}
//		
//		return new PriceDTO();
		
		DeletePriceResponse response = priceClient.deletePrice(id);
		
		if(response.getPrice().getPriceId() != null) {
			return priceConverter.convertToDTOFromClient(response.getPrice());
		}
		
		return new PriceDTO();
		
	}

	public List<PriceDTO> findAllByUnit(Long id) {
		
//		Optional< List<PriceLocal> > prices = Optional.of( priceRepository.findAllByAccommodationUnitLocalAccommodationUnitId(id) );
//		
//		ArrayList < PriceDTO > dtoPrices = new ArrayList<PriceDTO>();
//		
//		if ( prices.isPresent() ) {
//			
//			for ( PriceLocal price : prices.get() ) {
//				
//				dtoPrices.add(priceConverter.convertToDTO(price));
//				
//			}
//			
//			return dtoPrices;
//			
//		}
//			
//		return Collections.emptyList();
		
		GetPricesResponse response =  priceClient.GetPrices(id);
		List<PriceDTO> dtoPrices = new ArrayList<PriceDTO>();
		
		if(!response.getPrice().isEmpty()) {
			
			for ( Price price : response.getPrice() ) {
				dtoPrices.add(priceConverter.convertToDTOFromClient(price));	
			}
			
			return dtoPrices;
		}
		
		return Collections.emptyList();

		
	}
	
//	public Boolean sync(Long id) {
//		GetPricesResponse response =  priceClient.GetPrices(id);
//		PriceDTO dto = new PriceDTO();
//		
//		if(!response.getPrice().isEmpty()) {
//			
//			for ( Price price : response.getPrice() ) {
//				dto = priceConverter.convertToDTOFromClient(price);
//
//				PriceDTO returnedDTO = this.update(id, dto);
//			
//				if(returnedDTO.getPriceId() == null) {
//					this.save(dto);
//				}
//			}
//			
//			return true;
//		}
//		
//		return false;
//	}

}
