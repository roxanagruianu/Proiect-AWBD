package com.proiect.awbd;

import com.proiect.awbd.Services.UtilizatorService;
import com.proiect.awbd.dtos.UtilizatorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilizatorService utilizatorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilizatorDTO utilizator = utilizatorService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizatorul nu a fost găsit: " + username));

        List<GrantedAuthority> authorities = utilizator.getRoluri().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        System.out.println("User: " + utilizator.getUsername());
        System.out.println("Roles:");
        authorities.forEach(auth -> System.out.println(" - " + auth.getAuthority()));

        return new org.springframework.security.core.userdetails.User(
                utilizator.getUsername(),
                utilizator.getPassword(),
                authorities
        );
    }

}
