package at.fischelmayer.securitydemo.security.config;

import at.fischelmayer.securitydemo.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfigurer {

    private final JwtRequestFilter jwtRequestFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfigurer( JwtRequestFilter jwtRequestFilter,
                               UserDetailsService userDetailsService ) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
    }

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

    /**
     * for illustration, DaoAuthenticationProvider would be configured as default Provider
     * in the ProviderManager List with "auto configuration".
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder( passwordEncoder() );
        daoAuthenticationProvider.setUserDetailsService( userDetailsService );
        return daoAuthenticationProvider;
    }
}
