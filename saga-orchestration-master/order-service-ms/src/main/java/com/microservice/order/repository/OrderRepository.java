package com.microservice.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.order.DTO.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

}
