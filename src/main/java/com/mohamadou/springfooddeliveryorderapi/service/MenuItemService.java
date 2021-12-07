package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import com.mohamadou.springfooddeliveryorderapi.entity.MenuItem;
import com.mohamadou.springfooddeliveryorderapi.entity.Offer;
import com.mohamadou.springfooddeliveryorderapi.repository.CategoryRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.MenuItemRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.OfferRepository;
import com.mohamadou.springfooddeliveryorderapi.request.MenuItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {
    MenuItemRepository menuItemRepository;
    OfferRepository offerRepository;
    CategoryRepository categoryRepository;

    public MenuItemService() {

    }

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository,
                           OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public Optional<MenuItem> getMenuItemById(Long menuItemId) {
        return menuItemRepository.findById(menuItemId);
    }

    public int deleteMenuItemById(Long menuItemId) {
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(menuItemId);
        if(optionalMenuItem.isEmpty()) {
            throw new RuntimeException("Menu with id:" + menuItemId + " does not exists");
        }
        menuItemRepository.deleteById(menuItemId);
        return 0;
    }

    public MenuItem createMenuItem(MenuItemRequest menuItemRequest) {
        MenuItem menuItem = new MenuItem();
        menuItem.setItemName(menuItemRequest.getItemName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setIngredients(menuItemRequest.getIngredients());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setActive(menuItemRequest.getActive());

        //Check if Offer exists before adding it to the menu_item
        Optional<Offer> optionalOffer = offerRepository.findById(menuItemRequest.getOfferId());

        // TODO fix offer attaching to menutiem when creating new one
        optionalOffer.ifPresent(offer -> menuItem.setOffer(List.of(offer)));
        if(optionalOffer.isEmpty()){
            List<Offer> offers = new ArrayList<>();
            menuItem.setOffer(offers);
        }

        //Check if Category exists before adding it to the menu_item
        Optional<Category> optionalCategory = categoryRepository.findById(menuItemRequest.getCategoryId());
        optionalCategory.ifPresent(menuItem::setCategory);

        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(Long menuItemId, MenuItemRequest menuItemRequest) {
        //Check if Menu Item exists before updating it
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(menuItemId);
        if (optionalMenuItem.isEmpty()) {
            throw new RuntimeException("Menu with id:" + menuItemId + " does not exists");
        }

        MenuItem menuItem = optionalMenuItem.get();
        menuItem.setItemName(menuItemRequest.getItemName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setIngredients(menuItemRequest.getIngredients());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setActive(menuItemRequest.getActive());

        //Check if Offer exists before adding it to the menu_item
        Optional<Offer> optionalOffer = offerRepository.findById(menuItemRequest.getOfferId());
        optionalOffer.ifPresent(offer -> menuItem.setOffer(List.of(offer)));

        //Check if Category exists before adding it to the menu_item
        Optional<Category> optionalCategory = categoryRepository.findById(menuItemRequest.getOfferId());
        optionalCategory.ifPresent(menuItem::setCategory);

        return menuItemRepository.save(menuItem);
    }
}
