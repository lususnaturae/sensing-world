package com.ylitormatech.sensingworld.domain.service;

import com.ylitormatech.sensingworld.web.WwwUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by marco on 6.5.2016.
 */
public interface UserService extends UserDetailsService{

    public WwwUser find(String username);

    public WwwUser find(Integer id);

    public UserDetails loadUserByUsername(String username);

    public void add(WwwUser u);

    public List<WwwUser> findAll();
}
