package com.mohamadou.springfooddeliveryorderapi.controller;

import com.mohamadou.springfooddeliveryorderapi.entity.Offer;
import com.mohamadou.springfooddeliveryorderapi.request.OfferRequest;
import com.mohamadou.springfooddeliveryorderapi.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/offer")
public class OfferController {
    OfferService offerService;

    public OfferController() {

    }

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping(path = "{offerId}")
    public Offer getOfferById(@PathVariable Long offerId) {
        return offerService.getOfferById(offerId);
    }

    @DeleteMapping(path = "/delete/{offerId}")
    public int deleteOfferById(@PathVariable Long offerId) {
        return offerService.deleteOfferById(offerId);
    }

    @PostMapping(path = "/create")
    public Offer createOffer(@RequestBody OfferRequest offerRequest) {
        return offerService.createOffer(offerRequest);
    }

    @PutMapping(path = "/edit/{offerId}")
    public Offer updateOffer(@PathVariable Long offerId, @RequestBody OfferRequest offerRequest) {
        return offerService.updateOffer(offerId, offerRequest);
    }

    @PostMapping(path = "/addMenuItemToOffer/{offerId}/{menuItemId}")
    public Offer addMenuItemToOffer(@PathVariable Long offerId, @PathVariable Long menuItemId) {
        return offerService.addMenuItemToOffer(offerId, menuItemId);
    }
}
