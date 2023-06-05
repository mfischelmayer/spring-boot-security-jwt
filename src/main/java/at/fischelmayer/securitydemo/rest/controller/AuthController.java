package at.fischelmayer.securitydemo.rest.controller;

import at.fischelmayer.securitydemo.rest.models.AuthenticationRequest;
import at.fischelmayer.securitydemo.rest.models.AuthenticationResponse;
import at.fischelmayer.securitydemo.security.JwtUtil;
import at.fischelmayer.securitydemo.security.services.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtTokenUtil;

    public AuthController( AuthenticationManager authenticationManager,
                           CustomUserDetailsService userDetailsService,
                           JwtUtil jwtTokenUtil ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     *
     * @param authenticationRequest username and password
     * @return
     * @throws Exception
     */
    @PostMapping( "/auth" )
    public ResponseEntity<?> createAuthenticationToken( @RequestBody AuthenticationRequest authenticationRequest ) throws Exception {

        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( authenticationRequest.getUsername(), authenticationRequest.getPassword() ) );
        } catch ( BadCredentialsException e ) {
            throw new Exception( "Incorrect username or password", e );
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername( authenticationRequest.getUsername() );
        String jwt = jwtTokenUtil.generateToken( userDetails );
        return ResponseEntity.ok( new AuthenticationResponse( jwt ) );
    }
}
