package at.fischelmayer.securitydemo.security.services;

import at.fischelmayer.securitydemo.domain.User;
import at.fischelmayer.securitydemo.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        User user = userRepository.findByUsername( username );

        // full qualified name because of the same name of classes "User"
        return new org.springframework.security.core.userdetails.User( user.getUsername(),
                user.getPassword(),
                List.of( new SimpleGrantedAuthority( user.getRole().getName() ) ) );
    }
}
