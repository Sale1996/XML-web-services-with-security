package com.tim9.accommodationservice.services;

import java.util.Collections;

import com.tim9.accommodationserviceclient.dtos.AccommodationUnitsWithPricesDTO;

public class QuickSort { 
	
	 int partitionAsc(AccommodationUnitsWithPricesDTO arr, int low, int high) { 
	     float pivot = arr.getPrices().get(high);
	     int i = (low-1);
	     for (int j=low; j<high; j++) {
	    	 
	         if (arr.getPrices().get(j) <= pivot) { 
	             i++; 
	             
	             Collections.swap(arr.getPrices(), i, j);
	             Collections.swap(arr.getUnits(), i, j);
	         } 
	     } 
	     
	     Collections.swap(arr.getPrices(), i+1, high);
	     Collections.swap(arr.getUnits(), i+1, high);
	
	     return i+1; 
	 } 
	
	 void sortAsc(AccommodationUnitsWithPricesDTO arr, int low, int high) {
		 
	     if (low < high) { 
	    	 
	         int pi = partitionAsc(arr, low, high); 
	
	         sortAsc(arr, low, pi-1); 
	         sortAsc(arr, pi+1, high); 
	     } 
	 }
	 
	 int partitionDesc(AccommodationUnitsWithPricesDTO arr, int low, int high) { 
	     float pivot = arr.getPrices().get(high);
	     int i = (low-1);
	     for (int j=low; j<high; j++) {
	    	 
	         if (arr.getPrices().get(j) >= pivot) { 
	             i++; 
	             
	             Collections.swap(arr.getPrices(), i, j);
	             Collections.swap(arr.getUnits(), i, j);
	         } 
	     } 
	     
	     Collections.swap(arr.getPrices(), i+1, high);
	     Collections.swap(arr.getUnits(), i+1, high);
	
	     return i+1; 
	 } 
	
	 void sortDesc(AccommodationUnitsWithPricesDTO arr, int low, int high) {
		 
	     if (low < high) { 
	    	 
	         int pi = partitionDesc(arr, low, high); 
	
	         sortDesc(arr, low, pi-1); 
	         sortDesc(arr, pi+1, high); 
	     } 
	 }
} 
