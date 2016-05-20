package com.ylitormatech.sensingworld.domain.service.impl;

import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.domain.repository.UserRepository;
import com.ylitormatech.sensingworld.domain.service.UserService;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 6.5.2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    /*
    * In repository query with .getSingleResult(); causes exception if No entity found for query
    * getUserSanityCheck work with .getResultList();
    *
    * */


    public boolean getUserSanityCheck(String username){
        return userRepository.getUserSanityCheck(username);
    }

    public WwwUser getUser(String username) {
        UserEntity u = userRepository.getUser(username);
        return new WwwUser(new Long(u.getId()),u.getUsername(), u.getPassword(),u.getEmail(),u.getRole());
    }

    public WwwUser getUser(Long userId) {
        UserEntity u = userRepository.getUser(userId.intValue());
        return new WwwUser(new Long(u.getId()),u.getUsername(), u.getPassword(),u.getEmail(),u.getRole());
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return getUser(username);
    }

    @Transactional(readOnly = false)
    public void register(WwwUser u) {
        UserEntity dbu = new UserEntity();
        dbu.setEmail(u.getEmail());
        dbu.setPassword(u.getPassword());
        dbu.setRole(u.getRole());
        dbu.setUsername(u.getUsername());
        userRepository.store(dbu);
    }

    public List<WwwUser> getUsers() {
        List<UserEntity> users = userRepository.getUsers();
        ArrayList<WwwUser> wwwUserArrayList = new ArrayList<WwwUser>();
        if (users != null && !users.isEmpty()) {
            for (UserEntity u : users) {
                wwwUserArrayList.add(new WwwUser(new Long(u.getId()), u.getUsername(), u.getPassword(), u.getEmail(), u.getRole()));
            }
        }
        return wwwUserArrayList;
    }
}
