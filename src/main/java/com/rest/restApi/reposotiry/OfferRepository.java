package com.rest.restApi.reposotiry;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.restApi.entities.Offer;

public interface OfferRepository extends JpaRepository<Offer,Long>{
    
}