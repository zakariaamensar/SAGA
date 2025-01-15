package com.microservice.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.payment.DTO.UserAccount;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, String> {

}
