package com.fleet.fleet.service;

import com.fleet.fleet.domain.Manager;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.dto.userDto;
import com.fleet.fleet.repo.RepoManager;
import com.fleet.fleet.security.CustomUserDetails;
import com.fleet.fleet.security.JWTService;
import com.fleet.fleet.security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceManager {

   private final RepoManager repoManager;

   @Autowired
   private final AuthenticationManager authenticationManager;

   @Autowired
   private final JWTService jwtService;

   @Autowired
   private final MyUserDetailsService myUserDetailsService;

    public Manager postManager(Manager manager) {return repoManager.save(manager);}


    public String loginManager(userDto userDto) throws UsernameNotFoundException {
            CustomUserDetails  customUserDetails = (CustomUserDetails) myUserDetailsService.loadUserByUsername(userDto.getUsername());
            log.info("---{}",customUserDetails);
            return jwtService.generateToken(customUserDetails.getid(),customUserDetails.getUsername(),customUserDetails.getRole());

    }
}
