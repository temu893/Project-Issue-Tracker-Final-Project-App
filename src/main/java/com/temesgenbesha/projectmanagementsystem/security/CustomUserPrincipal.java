package com.temesgenbesha.projectmanagementsystem.security;

import com.temesgenbesha.projectmanagementsystem.entity.Role;
import com.temesgenbesha.projectmanagementsystem.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class CustomUserPrincipal implements UserDetails {

    //the interface UserDetails forces us to implements this methods
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // spring security doesn't work with our role entity it works with its own authority entity so i mapped the role
        //entity to grantedAuthority role entity

        //convert the roles to granted authorities by getting the name of the roles and map to the granted authority
        return user.getRoles().stream().map(Role::getName).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
