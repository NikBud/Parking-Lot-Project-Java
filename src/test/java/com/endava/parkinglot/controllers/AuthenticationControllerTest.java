package com.endava.parkinglot.controllers;

import com.endava.parkinglot.DTO.auth.AuthAndRegistrationResponseDTO;
import com.endava.parkinglot.DTO.auth.AuthenticationDTO;
import com.endava.parkinglot.security.JWTUtil;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
    @Mock
    private JWTUtil jwtUtil;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    Authentication authentication;
    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    void authenticate_WithValidData_ShouldAuthenticateUser() {
        AuthenticationDTO authenticationDTO = AuthenticationDTO.builder()
                .email(("vasilii@gmail.com"))
                .password("Vasilii_5")
                .build();

        when(authentication.getPrincipal()).thenReturn(createUserDetails());

        String jwtToken = "testToken";
        when(jwtUtil.generateAccessToken(authenticationDTO.getEmail())).thenReturn(jwtToken);

        AuthAndRegistrationResponseDTO expectedResponse = new AuthAndRegistrationResponseDTO(
                authenticationDTO.getEmail(),
                "ROLE_USER".substring(5),
                jwtToken
        );

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);


        ResponseEntity<AuthAndRegistrationResponseDTO> response = authenticationController.authenticate(authenticationDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertAll(
                () -> assertEquals(expectedResponse.getEmail(), Objects.requireNonNull(response.getBody()).getEmail()),
                () -> assertEquals(expectedResponse.getRole(), Objects.requireNonNull(response.getBody()).getRole()),
                () -> assertEquals(expectedResponse.getJwt(), Objects.requireNonNull(response.getBody()).getJwt())
        );

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil).generateAccessToken(authenticationDTO.getEmail());
    }

    private UserDetails createUserDetails() {
        String username = "vasilii@gmail.com";
        String password = "Vasilii_5";
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(username, password, authorities);
    }

    @Test
    void authenticate_WithInvalidData_ShouldThrowException() {
        AuthenticationDTO invalidAuthenticationDTO = AuthenticationDTO.builder()
                .email(("vasiliigmail.com"))
                .password("Vasi")
                .build();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(BadCredentialsException.class);

        assertThrows(BadCredentialsException.class, () -> {
            authenticationController.authenticate(invalidAuthenticationDTO);
        });

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoInteractions(jwtUtil);
    }
}