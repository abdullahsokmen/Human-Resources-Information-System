package com.group.config.security;

import com.group.exception.AuthServiceException;
import com.group.exception.EErrorType;
import com.group.repository.entity.Auth;
import com.group.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetails implements UserDetailsService {

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public UserDetails loadUserByUserId(Long id) throws UsernameNotFoundException {
        Optional<Auth>auth=authService.findById(id);

        if (auth.isPresent()){
            List<GrantedAuthority> authorityList =new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(auth.get().getRole().toString()));

            return User.builder()
                    .username(auth.get().getName())
                    .password("")
                    .accountExpired(false)
                    .accountLocked(false)
                    .authorities(authorityList)
                    .build();
        }
        throw new AuthServiceException(EErrorType.REGISTER_ERROR_USERNAME);
    }



}
