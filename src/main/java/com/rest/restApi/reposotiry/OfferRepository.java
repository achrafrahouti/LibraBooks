package com.rest.restApi.reposotiry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.rest.restApi.entities.Offer;

public interface OfferRepository extends JpaRepository<Offer,Long>{
    
    List<Offer> findByAddress(String address);
}