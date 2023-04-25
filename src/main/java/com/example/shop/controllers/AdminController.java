package com.example.shop.controllers;

import com.example.shop.helpers.TimeHelper;
import com.example.shop.models.ItemModel;
import com.example.shop.models.RequestModel;
import com.example.shop.repository.ItemRepository;
import com.example.shop.repository.RequestRepository;
import com.example.shop.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    FirebaseService firebaseService;

    @GetMapping
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/edit")
    public String getAll(Model model){
        List<ItemModel> list = itemRepository.findAll();
        model.addAttribute("items", list);
        list.stream().forEach(i -> i.setUrl(firebaseService.getUrl(i.getUrl())));
        list = TimeHelper.getTime(list);
        return "editItems";
    }

    @GetMapping("/requests")
    public String getPage(Model model){
        List<RequestModel> list = requestRepository.findAll();
        model.addAttribute("requests", list);
        return "requests";
    }
}
