package com.aleyna.notebook_app.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.aleyna.notebook_app.models.UserModel;

public class CustomUserDetails implements UserDetails {
 
    private UserModel user;
     
    public CustomUserDetails(UserModel user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
 
    public long getId()
    {
    	return user.getId();
    }
    
    @Override
    public String getPassword() {
        return user.getUserPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getUserName();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }

}
