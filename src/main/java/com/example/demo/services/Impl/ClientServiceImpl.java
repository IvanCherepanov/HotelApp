package com.example.demo.services.Impl;

import com.example.demo.model.dao.IClientRepository;
import com.example.demo.model.entity.Client;
import com.example.demo.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl extends AbstractServiceImpl<Client, IClientRepository> implements IClientService {
    @Autowired
    protected ClientServiceImpl(IClientRepository defaultDao) {
        super(defaultDao);
    }
}
