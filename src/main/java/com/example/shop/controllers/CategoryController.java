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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    FirebaseService firebaseService;
    @GetMapping("/toys")
    public String getToys(Model model){
        List<ItemModel> list = itemRepository.findAllByType("Игрушки");
        list = TimeHelper.getTime(list);
        list.stream().forEach(i -> i.setUrl(firebaseService.getUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/forpc")
    public String getForPc(Model model){
        List<ItemModel> list = itemRepository.findAllByType("Для компьютера");
        list = TimeHelper.getTime(list);
        list.stream().forEach(i -> i.setUrl(firebaseService.getUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/formyself")
    public String getForMySelf(Model model){
        List<ItemModel> list = itemRepository.findAllByType("Для себя");
        list = TimeHelper.getTime(list);
        list.stream().forEach(i -> i.setUrl(firebaseService.getUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
}
