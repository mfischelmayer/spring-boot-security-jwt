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

    /**
     * In our case the user store is a database.
     * It's possible to implement whatever we like as an data store for an user.
     * (e.g. a file, a directory service or whatever)
     *
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        User user = userRepository.findByUsername( username );

        // full qualified name because of the same name of classes "User"
        return new org.springframework.security.core.userdetails.User( user.getUsername(),
                user.getPassword(),
                List.of( new SimpleGrantedAuthority( user.getRole().getName() ) ) );
    }
}
