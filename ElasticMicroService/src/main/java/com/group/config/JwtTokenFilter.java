package com.group.config;


import com.eren.exception.EErrorType;
import com.eren.exception.ElasticServiceException;
import com.eren.utility.JwtTokenManager;
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
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String BearerToken =  request.getHeader("Authorization");
        /**
         * bu kisim filtreye takilan tum isteklerin incelenecgi yerdir.
         * Bu nedenle buraya gelen tum isteklerde Baslik(Header) kisminda Bearer tokeni ariyoruz.
         * Eger Bearer token yok ise(null) hata firlatiriz.
         * Eger gelen degerin icinde token yok ise hata firlatiriz.
         */
        if(SecurityContextHolder.getContext().getAuthentication()!=null) filterChain.doFilter(request,response);
        if(BearerToken==null || !BearerToken.startsWith("Bearer "))
            throw new ElasticServiceException(EErrorType.INVALID_TOKEN);
        String token = BearerToken.substring(7);
        Optional<Long> authId = jwtTokenManager.getByIdFromToken(token);
        if(authId.isEmpty())
            throw new ElasticServiceException(EErrorType.INVALID_TOKEN);
        /**
         * Eger tum kosullar dogru ise, buradan itibaren bilgileri kontrol edilen
         * kisiye ait Spring Security tarafindan kullanilacak lan bir kimlik karti hazirlamamiz gerekmektedir.
         */
        UserDetails userDetails = jwtUserDetails.getUserByAuthId(authId.get());
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request,response);


    }
}
