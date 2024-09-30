package com.aleyna.notebook_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aleyna.notebook_app.models.UserModel;
import com.aleyna.notebook_app.repositories.IUserInfoRepository;
import com.aleyna.notebook_app.security.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private IUserInfoRepository userRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepo.findByuserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
    
}
