package com.example.demo.services.Impl;

import com.example.demo.model.dao.IClientRepository;
import com.example.demo.model.entity.Client;
import com.example.demo.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl extends AbstractServiceImpl<Client, IClientRepository> implements IClientService, UserDetailsService {
    private IClientRepository iClientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    protected ClientServiceImpl(IClientRepository defaultDao) {
        super(defaultDao);
        this.iClientRepository = defaultDao;
    }

    @Override
    public String getUserRole(Authentication authentication) {
        System.out.println(120);
        if (authentication == null)
            return "GUEST";
        else
            System.out.println(130);
        System.out.println();
            System.out.println(authentication.getName());
            return ((Client) loadUserByUsername(authentication.getName())).getRole();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  iClientRepository.findClientByName(username);
        if(user != null){
            return user;
        }
        throw new
                UsernameNotFoundException("User not exist with name :" +username);
    }
    public Client getClientByLogin(String username){
        return iClientRepository.findClientByName(username);
    }

    @Override
    public Client update(Long id, Client  entity) {
        findById(id);
        entity.setId(id);
        return create(entity);
    }

    @Override
    public void create(String email,
                       String login,
                       String username,
                       String address,
                       String numberPhone,
                       String password,
                       String role) {
        Client client = new Client();
        client.setEmail(email);
        client.setFullName(username);
        client.setName(login);
        client.setAddress(address);
        client.setPhone(numberPhone);
        client.setPassword(bCryptPasswordEncoder.encode(password));
        client.setRole(role);
        iClientRepository.save(client);
    }



    @Override
    public Long getUserId(Authentication authentication) {
        if (authentication == null)
            return -1l;
        else
            return ((Client) loadUserByUsername(authentication.getName())).getId();
    }


    @Bean
    public void createDef(){
        Client user = new Client();
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        user.setEmail("email");
        user.setName("admin");
        user.setRole("ADMIN");
        user.setFullName("anton");
        user.setAddress("London");
        user.setPhone("89324");
        if (iClientRepository.findClientByName(user.getName())==null) {
            iClientRepository.save(user);
        }
    }
}
