package com.group.config.security;


import com.group.exception.AdminServiceException;
import com.group.exception.EErrorType;
import com.group.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails;





    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");
        System.out.println("=====>"+authHeader);
        if (authHeader!=null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            Optional<String>userRole=jwtTokenManager.getRoleFromToken(token);
            if (userRole.isPresent()){
                UserDetails userDetails= jwtUserDetails.loadUserByUserRole(userRole.get());
                UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else {
               throw new AdminServiceException(EErrorType.INVALID_TOKEN);
            }
        }
        filterChain.doFilter(request,response);
    }
}
