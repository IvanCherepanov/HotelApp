package com.example.demo.services;

import com.example.demo.model.entity.Client;
import org.springframework.security.core.Authentication;

public interface IClientService extends IAbstractService<Client> {
    void create(String email,String login, String username,String address,
                String numberPhone,String password, String role);
    String getUserRole(Authentication authentication);
    Long getUserId(Authentication authentication);
}
