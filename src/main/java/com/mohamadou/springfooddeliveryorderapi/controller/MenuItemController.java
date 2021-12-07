package com.mohamadou.springfooddeliveryorderapi.controller;

import com.mohamadou.springfooddeliveryorderapi.entity.MenuItem;
import com.mohamadou.springfooddeliveryorderapi.request.MenuItemRequest;
import com.mohamadou.springfooddeliveryorderapi.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "menuItem")
public class MenuItemController {

    public MenuItemService menuItemService;

    public MenuItemController() {

    }

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping(path = "{menuItemId}")
    public Optional<MenuItem> getMenuItemById(@PathVariable Long menuItemId) {
        return menuItemService.getMenuItemById(menuItemId);
    }

    @DeleteMapping(path = "/delete/{menuItemId}")
    public int deleteMenuItemById(@PathVariable Long menuItemId) {
        return menuItemService.deleteMenuItemById(menuItemId);
    }

    @PostMapping(path = "/create")
    public MenuItem createMenuItem(@RequestBody MenuItemRequest menuItemRequest) {
        return menuItemService.createMenuItem(menuItemRequest);
    }

    @PutMapping(path = "/edit/{menuItemId}")
    public MenuItem updateMenuItem(@RequestBody MenuItemRequest menuItemRequest, @PathVariable Long menuItemId) {
        return menuItemService.updateMenuItem(menuItemId, menuItemRequest);
    }
}
