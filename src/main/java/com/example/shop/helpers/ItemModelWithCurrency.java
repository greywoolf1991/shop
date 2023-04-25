package com.example.shop.helpers;

import com.example.shop.models.ItemModel;
import lombok.Data;

@Data
public class ItemModelWithCurrency {
        private Long id;
        private String name;
        double price;
        private String description;
        private String url;
        Long time;
        private String type;
        private String timeFormat;
        private double usdPrice;
        private double eurPrice;

    public ItemModelWithCurrency(ItemModel itemModel) {
        this.id = itemModel.getId();
        this.name = itemModel.getName();
        this.price = itemModel.getPrice();
        this.description = itemModel.getDescription();
        this.url = itemModel.getUrl();
        this.time = itemModel.getTime();
        this.type = itemModel.getType();
        this.timeFormat = itemModel.getTimeFormat();
    }
}
