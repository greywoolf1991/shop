package com.example.shop.controllers;

import com.example.shop.helpers.TimeHelper;
import com.example.shop.models.ItemModel;
import com.example.shop.repository.ItemRepository;
import com.example.shop.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/allItems")
public class AllItemsController {
    private final ItemRepository itemRepository;
    @Autowired
    private FirebaseService firebaseService;
    public AllItemsController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String getAllItems(Model model){
        List<ItemModel> list = itemRepository.findAll();
        list = TimeHelper.getTime(list);
        list.stream().forEach(i -> i.setUrl(firebaseService.getUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
}
