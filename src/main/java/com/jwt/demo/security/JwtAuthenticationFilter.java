package com.jwt.demo.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired private UserDetailsService userDetailsService;
  @Autowired private JwtTokenHelper jwtTokenHelper;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authorizationToken = request.getHeader("Authorization");
    String username = null;
    String token = null;
    if (authorizationToken != null && authorizationToken.startsWith("Bearer")) {

      token = authorizationToken.substring(7);
      try {
        username = jwtTokenHelper.getUserNameFromToken(token);
      } catch (IllegalArgumentException illegalArgumentException) {
        System.out.println("Unable to taken");
      } catch (ExpiredJwtException expiredJwtException) {
        System.out.println("Token has expired");
      } catch (MalformedJwtException malformedJwtException) {
        System.out.println("Invalid Jwt token");
      }

    } else {
      System.out.println("Token doesn't begin with bearer");
    }

    if (username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails user = this.userDetailsService.loadUserByUsername(username);
      if (this.jwtTokenHelper.validateToken(token, user)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      } else {
        System.out.println("Invalid Jwt token");
      }
    } else {
      System.out.println("Username is null or context is not null");
    }

    filterChain.doFilter(request, response);
  }
}
