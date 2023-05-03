package com.group.config;

import com.eren.repository.entity.UserProfile;
import com.eren.service.UserProfileService;
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
    UserProfileService userProfileService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserByAuthId(Long authid) {
        Optional<UserProfile> userProfile = userProfileService.findByAuthId(authid);
        if(userProfile.isEmpty()) return null;
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("Admin"));
        grantedAuthorityList.add(new SimpleGrantedAuthority("OzelMusteri"));
        grantedAuthorityList.add(new SimpleGrantedAuthority("VIP"));
        return User.builder().username(userProfile.get().getUsername())
                .accountLocked(false)
                .accountExpired(false)
                .authorities(grantedAuthorityList)
                .build();
    }
}
