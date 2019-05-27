package com.tim9.accommodationserviceclient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.accommodationserviceclient.dtos.PriceDTO;


@FeignClient(name="priceClient", url = "https://localhost:8081/price")
public interface PriceClient{
	
	@GetMapping("")
	public ResponseEntity< List<PriceDTO> > getAllPrices ();
	
	@GetMapping("/{id}")
	public ResponseEntity< PriceDTO > getOnePriceById (@PathVariable("id") Long id);
	
	@PostMapping("")
	public ResponseEntity< PriceDTO > addPrice ( @RequestBody PriceDTO dto );
	
	@PutMapping("/{id}")
	public ResponseEntity< PriceDTO > updatePrice (@PathVariable("id") Long id, @RequestBody PriceDTO priceDTO );
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity< PriceDTO > deletePrice (@PathVariable("id") Long id);

}
