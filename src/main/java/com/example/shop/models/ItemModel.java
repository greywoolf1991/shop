package com.example.shop.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity //делает из класса базу данных
@Data //задает дефолтный конструктор, геттеры и сеттеры
@Table(name="item_model") //задаем название таблицы
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //инкремент
    private Long id;
    @Column(name = "title")
    private String name;
    @Column(name = "price")
    double price;
    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
    @Column(name = "addTime")
    Long time;
    @Column(name = "type")
    private String type;

    private String timeFormat;
}
