package com.mati.twittersocial.controller;

import com.mati.twittersocial.config.JwtProvider;
import com.mati.twittersocial.model.User;
import com.mati.twittersocial.repository.UserRepository;
import com.mati.twittersocial.request.LoginRequest;
import com.mati.twittersocial.response.AuthResponse;
import com.mati.twittersocial.service.CustomUserDetailsService;
import com.mati.twittersocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")

    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByEmail(user.getEmail());

        if (isExist!=null){
            throw new Exception("email aready used in another account");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "register success");

        return res;
    }
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication =
                authenticate(loginRequest.getEmail(),loginRequest.getPassword());


        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "login success");

        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if (userDetails==null){
            throw new BadCredentialsException("ivalid username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("password not matched");
        }

        return  new UsernamePasswordAuthenticationToken(userDetails,
                null,
                            userDetails.getAuthorities());
    }

}
