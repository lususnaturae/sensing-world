package com.ylitormatech.sensingworld.domain.service.impl;

import com.ylitormatech.sensingworld.domain.entity.RoleEntity;
import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.domain.repository.RoleRepository;
import com.ylitormatech.sensingworld.domain.repository.UserRepository;
import com.ylitormatech.sensingworld.domain.service.UserService;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by marco on 6.5.2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public WwwUser find(String username) {
        UserEntity u = userRepository.find(username);
        return new WwwUser(new Long(u.getId()),u.getUsername(), u.getPassword(),u.getEmail(),u.getRoles());
    }

    @Override
    public WwwUser find(Integer id) {
        UserEntity u = userRepository.find(id.intValue());
        return new WwwUser(new Long(u.getId()),u.getUsername(), u.getPassword(),u.getEmail(),u.getRoles());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return find(username);
    }

    @Override
    @Transactional
    public void add(WwwUser u) {
        UserEntity dbu = new UserEntity();
        dbu.setEmail(u.getEmail());
        dbu.setPassword(passwordEncoder.encode(u.getPassword()));

        Iterator<String> i = u.getRoleNames().iterator();
        Set<RoleEntity> roles = new HashSet<>();
        while (i.hasNext()) {
            roles.add(roleRepository.find(i.next()));
        }
        dbu.setRoles(roles);

        dbu.setUsername(u.getUsername());
        userRepository.add(dbu);
    }

    @Override
    public List<WwwUser> findAll() {
        List<UserEntity> users = userRepository.findAll();
        ArrayList<WwwUser> wwwUserArrayList = new ArrayList<WwwUser>();
        if (users != null && !users.isEmpty()) {
            for (UserEntity u : users) {
                wwwUserArrayList.add(new WwwUser(new Long(u.getId()), u.getUsername(), u.getPassword(), u.getEmail(), u.getRoles()));
            }
        }
        return wwwUserArrayList;
    }
}
