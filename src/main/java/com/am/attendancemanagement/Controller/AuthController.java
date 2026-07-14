package com.am.attendancemanagement.Controller;

import com.am.attendancemanagement.Config.JwtService;
import com.am.attendancemanagement.dto.LoginRequest;
import com.am.attendancemanagement.dto.LoginResponse;
import com.am.attendancemanagement.entity.User;
import com.am.attendancemanagement.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtService jwtService,
            UserRepository userRepository) {

        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        request.getUsername()
                );

        String token = jwtService.generateToken(userDetails);

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );

        return ResponseEntity.ok(
                new LoginResponse(
                        token,
                        user.getRole()
                )
        );
    }
}