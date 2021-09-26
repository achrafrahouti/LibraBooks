package com.rest.restApi.services;

import java.util.List;

import com.rest.restApi.entities.Offer;

public interface OfferService {
    Offer save();
    List<Offer> getAll();
    Offer getById();
    
}