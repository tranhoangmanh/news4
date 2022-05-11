package com.group4.group4.controller;

import com.group4.group4.dto.UserDTO;
import com.group4.group4.entity.RoleEntity;
import com.group4.group4.entity.UserEntity;
import com.group4.group4.jwt.JwtTokenProvider;
import com.group4.group4.payload.request.LoginRequest;
import com.group4.group4.payload.request.RegisterRequest;
import com.group4.group4.payload.response.LoginResponse;
import com.group4.group4.repository.IRoleRepository;
import com.group4.group4.repository.IUserRepository;
import com.group4.group4.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class HomeController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IRoleRepository iRoleRepository;

    @PostMapping("/login")
    public LoginResponse getResponseAfterLogin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtTokenProvider.generateTokenFormEmail(userDetails.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return LoginResponse.builder()
                .id(userDetails.getUserEntity().getId())
                .email(userDetails.getUsername())
                .accessToken(jwtToken)
                .tokenType(new LoginResponse().getTokenType())
                .role(roles)
                .build();
    }

    @PostMapping("/register")
    public RegisterRequest register(@RequestBody RegisterRequest request){
        RoleEntity roleUser = iRoleRepository.getById(2L);
        List<RoleEntity> listRoleUser = new ArrayList<>();
        listRoleUser.add(roleUser);
        iUserRepository.save(new UserEntity(null,request.getEmail(),passwordEncoder.encode(request.getPassword()),null,listRoleUser));
        return request;
    }
//    @CrossOrigin(origins = "http://10.0.0.96:3000")
    @GetMapping("/add")
    public String addNewUser(){
        RoleEntity roleAdmin = iRoleRepository.getById(1L);
        RoleEntity roleUser = iRoleRepository.getById(2L);
        RoleEntity roleMentor = iRoleRepository.getById(3L);
        List<RoleEntity> listRoleAdmin = new ArrayList<>();
        listRoleAdmin.add(roleAdmin);
        List<RoleEntity> listRoleUser = new ArrayList<>();
        listRoleUser.add(roleUser);
        List<RoleEntity> listRoleMentor = new ArrayList<>();
        listRoleMentor.add(roleMentor);
        iUserRepository.save(new UserEntity(null,"admin@gmail.com",passwordEncoder.encode("123456"),null,listRoleAdmin));
        iUserRepository.save(new UserEntity(null,"user@gmail.com",passwordEncoder.encode("123456"),null,listRoleUser));
        iUserRepository.save(new UserEntity(null,"mentor@gmail.com",passwordEncoder.encode("123456"),null,listRoleMentor));
        return null;
    }
    @GetMapping("/test")
    public ResponseEntity<?> testCors(){
        return ResponseEntity.ok().body(new LoginRequest("ahihi","ahoho"));
    }
}
