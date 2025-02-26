package com.mohit.expensetracker.auth.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mohit.expensetracker.auth.service.JwtService;
import com.mohit.expensetracker.auth.service.UserDetailsImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ApplicationContext context;
    @Override
    protected void doFilterInternal( @NotNull HttpServletRequest request,
                                     @NotNull HttpServletResponse response,
                                     @NotNull FilterChain filterChain) throws IOException, ServletException{
        String authHeader=request.getHeader("Authorization");
        String username=null;
        String token=null;
        if(authHeader!=null&&authHeader.startsWith("Bearer")){
               
       token=authHeader.substring(7);

       username=jwtService.extractUsername(token);
        }
     
      
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()!=null){
            UserDetails userDetails=context.getBean(UserDetailsImpl.class).loadUserByUsername(username);
            if(jwtService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken( userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);       

}
    
}
