package com.ylitormatech.sensingworld.domain.service;

import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by marco on 6.5.2016.
 */
public interface UserService extends UserDetailsService{

    public boolean getUserSanityCheck(String username);

    public boolean getUserSanityCheck(Integer userId);

    public WwwUser getUser(String username);

    public WwwUser getUser(Integer userId);

    public UserEntity getUserEntity(Integer userId);

    public UserDetails loadUserByUsername(String username);

    public void register(WwwUser u);

    public void updateUser(WwwUser u);

    public void removeUser(Integer id);

    public List<WwwUser> getUsers();
}
