package com.example.demo.model.dao;

import com.example.demo.model.entity.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends IAbstractRepository<Client>{
    Client findClientByName(String login);
    Client findClientByFullName(String fullName);
    Client findClientsByNameAndPhoneAndEmail(String name, String phone, String email);
}
