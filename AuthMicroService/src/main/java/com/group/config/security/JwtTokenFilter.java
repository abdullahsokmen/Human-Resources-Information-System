package com.group.config.security;


import com.group.exception.AuthManagerException;
import com.group.exception.EErrorType;
import com.group.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private JwtUserDetails jwtUserDetails;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader= request.getHeader("Authorization");

        if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")&&
                SecurityContextHolder.getContext().getAuthentication()==null){
            String token=authorizationHeader.substring(7);
            Optional<Long>id=jwtTokenManager.getIdFromToken(token);
            if (id.isEmpty())
                throw new AuthManagerException(EErrorType.INVALID_TOKEN);
            UserDetails userDetails=jwtUserDetails.loadUserByUserId(id.get());
            if (Objects.isNull(userDetails))
                throw new AuthManagerException(EErrorType.INVALID_TOKEN);
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
