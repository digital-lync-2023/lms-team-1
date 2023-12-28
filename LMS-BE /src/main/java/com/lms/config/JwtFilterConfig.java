package com.lms.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilterConfig extends OncePerRequestFilter {

	@Autowired
	private JwtService js;

	@Autowired
	private UserUserdetailsService uds;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwtHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;

		if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
			token = jwtHeader.substring(7);
			userName = js.extractUsername(token);
		}
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = uds.loadUserByUsername(userName);
			if (js.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken at = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				at.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(at);
			}
		}
		filterChain.doFilter(request, response);
	}
}