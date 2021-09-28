package com.rest.restApi.reposotiry;

import java.util.List;

import com.rest.restApi.entities.Offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long>{
    
    List<Offer> findByAddress(String address);
}