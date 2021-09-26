package com.rest.restApi.controllers;

import java.util.ArrayList;
import java.util.List;

import com.rest.restApi.entities.Offer;
import com.rest.restApi.exceptions.OfferNotFoundException;
import com.rest.restApi.services.OfferService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    OfferService repository;

    @GetMapping
    public ResponseEntity<List<Offer>> getAll() {
        LOGGER.info("Request to retrieve a list of offer");
        try {
            List<Offer> items = new ArrayList<Offer>();

            repository.getAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Offer> getById(@PathVariable("id") Long id) throws OfferNotFoundException {
        LOGGER.info("Request to retrieve a offer by id = " + id);
        Offer offer = repository.getById(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Offer> create(@RequestBody Offer offer) {
        LOGGER.info("Request to Save  a offer {}}", offer);

        try {
            offer.setAddress(offer.getAddress().toUpperCase());
            Offer savedOffer = repository.save(offer);
            return new ResponseEntity<>(savedOffer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Offer> update(@PathVariable("id") Long id, @RequestBody Offer offer)
            throws OfferNotFoundException {
        LOGGER.info("Request to Update a offer  id = " + id);

        return new ResponseEntity<>(repository.update(id, offer), HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws OfferNotFoundException {
        LOGGER.info("Request to Delete  a offer by id = " + id);

        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(value = "/address", produces = { "application/json" })
    public List<Offer> getBooksByAuthor(@RequestParam(value = "address") String address) {
        LOGGER.info("Get List of Offer with a address= {}", address);
        return repository.findByAddress(address.toUpperCase());
    }
}