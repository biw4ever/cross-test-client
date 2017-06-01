package com.yjz.cross.test.client;

import org.springframework.stereotype.Service;

import com.yjz.cross.client.annotation.CrossReference;
import com.yjz.cross.test.intf.UserService;

@Service
public class ClientService
{
    @CrossReference 
    public UserService userService;
    
    public String getUserName()
    {
        return userService.getUserName();
    }
    
    
}
