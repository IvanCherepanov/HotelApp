package com.example.demo.services.Impl;

import com.example.demo.model.DAO.IClientRepository;
import com.example.demo.model.entity.Client;
import com.example.demo.services.IClientService;

public class ClientServiceImpl extends AbstractServiceImpl<Client, IClientRepository> implements IClientService {
    protected ClientServiceImpl(IClientRepository defaultDao) {
        super(defaultDao);
    }
}
