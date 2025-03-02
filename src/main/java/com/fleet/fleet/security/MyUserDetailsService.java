package com.fleet.fleet.security;

import com.fleet.fleet.domain.Manager;
import com.fleet.fleet.domain.ManagerFleet;
import com.fleet.fleet.repo.RepoManager;
import com.fleet.fleet.repo.RepoManagerFleet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final RepoManagerFleet repoManagerFleet;
    private final RepoManager repoManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = repoManager.findByUserName(username);
        if (manager != null) {
            return new CustomUserDetails(manager.getUserName(), manager.getId(), manager.getRole());
        }

        ManagerFleet managerFleet = repoManagerFleet.findBynameEts(username);
        if (managerFleet != null) {
            return new CustomUserDetails(managerFleet.getNameEts(), managerFleet.getId(), managerFleet.getRole());
        }

        throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
    }
}
