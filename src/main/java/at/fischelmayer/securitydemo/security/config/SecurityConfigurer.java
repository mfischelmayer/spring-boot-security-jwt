package at.fischelmayer.securitydemo.security.config;

import at.fischelmayer.securitydemo.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfigurer {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        return http.csrf( crsf -> crsf.disable() )
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers( "/auth" ).permitAll()
                        .requestMatchers( "/hello" ).permitAll()
                        .requestMatchers( "/admin" ).hasRole( "ADMIN" ) // the method hasRole add the ROLE_ prefix automatically
                        .anyRequest().authenticated() )
                .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
                .addFilterBefore( jwtRequestFilter, UsernamePasswordAuthenticationFilter.class )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration ) throws Exception {
        // form-based login, LDAP authentication, OAuth, etc., everything is possible
        // the default is a DaoAuthenticationProvider
        // the DaoAuthenticationProvider need a implementation of UserDetailsService
        // which is responsible for the actual implementation to load a user
        return authenticationConfiguration.getAuthenticationManager();
    }
}
