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

    public WwwUser getUser(String username) {
        UserEntity u = userRepository.getUser(username);
        return new WwwUser(new Long(u.getId()),u.getUsername(), u.getPassword(),u.getEmail(),u.getRoles());
    }

    public WwwUser getUser(Long userId) {
        UserEntity u = userRepository.getUser(userId.intValue());
        return new WwwUser(new Long(u.getId()),u.getUsername(), u.getPassword(),u.getEmail(),u.getRoles());
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return getUser(username);
    }

    @Transactional
    public void register(WwwUser u) {
        UserEntity dbu = new UserEntity();
        dbu.setEmail(u.getEmail());
        dbu.setPassword(passwordEncoder.encode(u.getPassword()));

        Iterator<String> i = u.getRoleNames().iterator();
        Set<RoleEntity> roles = new HashSet<>();
        while (i.hasNext()) {
            roles.add(roleRepository.getRole(i.next()));
        }
        dbu.setRoles(roles);

        dbu.setUsername(u.getUsername());
        userRepository.store(dbu);
    }

    public List<WwwUser> getUsers() {
        List<UserEntity> users = userRepository.getUsers();
        ArrayList<WwwUser> wwwUserArrayList = new ArrayList<WwwUser>();
        if (users != null && !users.isEmpty()) {
            for (UserEntity u : users) {
                wwwUserArrayList.add(new WwwUser(new Long(u.getId()), u.getUsername(), u.getPassword(), u.getEmail(), u.getRoles()));
            }
        }
        return wwwUserArrayList;
    }
}
