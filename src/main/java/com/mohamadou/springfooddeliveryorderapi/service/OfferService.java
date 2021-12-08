package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.MenuItem;
import com.mohamadou.springfooddeliveryorderapi.entity.Offer;
import com.mohamadou.springfooddeliveryorderapi.repository.MenuItemRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.OfferRepository;
import com.mohamadou.springfooddeliveryorderapi.request.OfferRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OfferService {
    OfferRepository offerRepository;
    MenuItemRepository menuItemRepository;

    public OfferService() {

    }

    @Autowired
    public OfferService(OfferRepository offerRepository, MenuItemRepository menuItemRepository) {
        this.offerRepository = offerRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Optional<Offer> getOfferById(Long offerId) {
        return offerRepository.findById(offerId);
    }

    public int deleteOfferById(Long offerId) {
        Optional<Offer> offerOptional = offerRepository.findById(offerId);
        if (offerOptional.isEmpty()) {
            throw new IllegalArgumentException("Offer with this id "+offerId+" does not exists");
        }
         offerRepository.deleteById(offerId);
         return 0;
    }

    public Offer createOffer(OfferRequest offerRequest) {
        Offer offer = new Offer();
        offer.setDateActiveFrom(offerRequest.getDateActiveFrom());
        offer.setDateActiveTo(offerRequest.getDateActiveTo());
        offer.setTimeActiveFrom(offerRequest.getTimeActiveFrom());
        offer.setTimeActiveTo(offerRequest.getTimeActiveTo());
        offer.setOfferPrice(offerRequest.getOfferPrice());

        // TODO : set menuItem

        return offerRepository.save(offer);
    }

    public Offer updateOffer(Long offerId, OfferRequest offerRequest) {
        Optional<Offer> offerOptional = offerRepository.findById(offerId);
        if (offerOptional.isEmpty()) {
            throw new IllegalArgumentException("Offer with this id "+offerId+" does not exists");
        }

        Offer offer = offerOptional.get();
        offer.setDateActiveFrom(offerRequest.getDateActiveFrom());
        offer.setDateActiveTo(offerRequest.getDateActiveTo());
        offer.setTimeActiveFrom(offerRequest.getTimeActiveFrom());
        offer.setTimeActiveTo(offerRequest.getTimeActiveTo());
        offer.setOfferPrice(offerRequest.getOfferPrice());

        // TODO : set menuItem

        return offerRepository.save(offer);
    }

    public Offer addMenuItemToOffer(Long offerId, Long menuItemId) {
        //Check if Offer exists before updating it
        Optional<Offer> offerOptional = offerRepository.findById(offerId);
        if (offerOptional.isEmpty()) {
            throw new IllegalArgumentException("Offer with this id "+offerId+" does not exists");
        }

        //Check if Menu Item exists before updating it
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(menuItemId);
        if (optionalMenuItem.isEmpty()) {
            throw new RuntimeException("Menu with id:" + menuItemId + " does not exists");
        }

        Offer offer = offerOptional.get();

        MenuItem menuItem = optionalMenuItem.get();

        offer.addMenuItem(menuItem);

        //offer.setMenuItems(offer.getMenuItems());

        Offer savedOffer = offerRepository.save(offer);
        log.info("Get items of this offer: {}",savedOffer.getMenuItems());

        return savedOffer;
    }
}
