package com.rest.restApi.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.rest.restApi.entities.Offer;
import com.rest.restApi.exceptions.OfferNotFoundException;

public interface OfferService {
    
    Offer save(@NotNull @Valid Offer offer);
    List<Offer> getAll();
    Offer getById(Long id) throws OfferNotFoundException;
    void deleteById(Long id) throws OfferNotFoundException;
    Offer update(Long id,Offer offer) throws OfferNotFoundException;
    List<Offer> findByAddress(String address) ;


}