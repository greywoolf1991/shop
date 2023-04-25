package com.example.shop.controllers;

import com.example.shop.models.ItemModel;
import com.example.shop.repository.ItemRepository;
import com.example.shop.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/edit")
public class EditController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    FirebaseService firebaseService;
    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable long id){
        ItemModel itemModel = this.itemRepository.findById(id);
        itemModel.setUrl(firebaseService.getUrl(itemModel.getUrl()));
        model.addAttribute("item", itemModel);
        return "edit";
    }
    @PostMapping("/{id}")
    public RedirectView setChanges(@PathVariable long id,
                                   @RequestParam String name, double price, String description, String url){
        ItemModel itemModel = this.itemRepository.findById(id);
        itemModel.setName(name);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setUrl(url);
        this.itemRepository.save(itemModel);
        return new RedirectView("/");
    }
}
