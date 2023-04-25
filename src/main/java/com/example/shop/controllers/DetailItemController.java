package com.example.shop.controllers;

import com.example.shop.helpers.ItemModelWithCurrency;
import com.example.shop.models.ItemModel;
import com.example.shop.models.RequestModel;
import com.example.shop.repository.ItemRepository;
import com.example.shop.repository.RequestRepository;
import com.example.shop.services.CurrencyService;
import com.example.shop.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/item")
public class DetailItemController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CurrencyService currencyService;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/{id}")
    public String getDetailPage(@PathVariable long id,
                                Model model){
        ItemModel itemModel = itemRepository.findById(id);
        ItemModelWithCurrency itemModelWithCurrency = new ItemModelWithCurrency(itemModel);
        itemModelWithCurrency.setEurPrice(currencyService.getEurPrice(itemModel.getPrice()));
        itemModelWithCurrency.setUsdPrice(currencyService.getUsdPrice(itemModel.getPrice()));
        itemModelWithCurrency.setUrl(firebaseService.getUrl(itemModelWithCurrency.getUrl()));
        model.addAttribute("item", itemModelWithCurrency);
        return "detailitem";
    }
    @PostMapping("/{id}")
    public RedirectView saveDate(@PathVariable long id,
                                 @RequestParam String name, String phoneNumber){
        RequestModel requestModel = new RequestModel();
        requestModel.setName(name);
        requestModel.setPhoneNumber(phoneNumber);
        requestModel.setItemId(id);
        requestRepository.save(requestModel);
        return new RedirectView("/allItems");
    }
}
