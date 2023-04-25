package com.example.shop.controllers;

import com.example.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/delete")
public class DeleteController {
    @Autowired
    ItemRepository itemRepository;
    @GetMapping("/{id}")
    public RedirectView delete(@PathVariable long id){
        itemRepository.deleteById(id);
        return new RedirectView("/admin/edit");
    }
}
