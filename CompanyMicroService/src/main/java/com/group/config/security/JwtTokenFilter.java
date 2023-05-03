package com.group.config.security;


import com.group.exception.CompanyManagerException;
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
import java.util.Objects;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails;





    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");
        if (authHeader!=null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            Optional<String>userRole=jwtTokenManager.getRoleFromToken(token);
            if (userRole.isEmpty())
                throw new CompanyManagerException(EErrorType.INVALID_TOKEN);
            Optional<String> userStatus = jwtTokenManager.getStatusFromToken(token);
            if (userStatus.isEmpty())
                throw new CompanyManagerException(EErrorType.INVALID_TOKEN);
            if (!userStatus.get().equals("ACTIVE"))
                throw new CompanyManagerException(EErrorType.USER_NOT_ACTIVE);
            UserDetails userDetails=jwtUserDetails.loadUserByRole(userRole.get());
            if (Objects.isNull(userDetails))
                throw new CompanyManagerException(EErrorType.INVALID_TOKEN);
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
