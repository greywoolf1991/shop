package com.example.shop.controllers;

import com.example.shop.models.ItemModel;
import com.example.shop.repository.ItemRepository;
import com.example.shop.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/addItem")
public class AddItemController {
    @Autowired //спринг сделает из этого репозитория нужный нам обьект
    ItemRepository itemRepository;
    @Autowired
    FirebaseService firebaseService;
    @GetMapping
    public String getAddItem(){
        return "addItem";
    }
    @PostMapping
    public RedirectView addData(@RequestParam String name,
                                String price,
                                String description,
                                MultipartFile file,
                                String type) throws Exception {
        ItemModel itemModel = new ItemModel();
        itemModel.setName(name);
        try {
            itemModel.setPrice(Double.parseDouble(price));
        }catch (Exception e){
            itemModel.setPrice(9999);
        }
        itemModel.setDescription(description);
        itemModel.setUrl(this.firebaseService.save(file));
        itemModel.setTime(Long.valueOf(System.currentTimeMillis()));
        itemModel.setType(type);
        this.itemRepository.save(itemModel);
        return new RedirectView("/");
    }
}
