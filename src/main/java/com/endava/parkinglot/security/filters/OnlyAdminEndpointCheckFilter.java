package com.endava.parkinglot.security.filters;

import com.endava.parkinglot.enums.Role;
import com.endava.parkinglot.exceptions.UserNotGrantedToDoActionException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OnlyAdminEndpointCheckFilter extends OncePerRequestFilter {

    private final List<String> adminEndpoints;

    public OnlyAdminEndpointCheckFilter() {
        this.adminEndpoints = new ArrayList<>(
                List.of(
                        "http://localhost:8080/api/register/grantAdmin",
                        "http://localhost:8080/api/parkingLot/id/addUser"
                )
        );
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String endpoint = request.getRequestURL().toString();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && adminEndpoints.contains(endpoint)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = "";
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                role = authority.toString();
            }
            role = role.substring(5);

            if (!role.equals(Role.ADMIN.toString()))
                throw new UserNotGrantedToDoActionException("User doesn't have authorities to do this action !");

        }
        filterChain.doFilter(request, response);
    }
}
