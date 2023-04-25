package com.example.shop.repository;

import com.example.shop.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestModel, Long> {

}
