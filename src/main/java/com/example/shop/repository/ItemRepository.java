package com.example.shop.repository;

import com.example.shop.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    ItemModel findById(long id);

    List<ItemModel> findAllByType(String type);
}
