package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import service.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/adminpanel/**").hasRole("ADMIN")
                                .requestMatchers("/adminall/**").hasRole("ADMIN")
                                .requestMatchers("/userpanel/**", "/userpanel/home/**", "/usertags/**").hasRole("USER")
                                .requestMatchers("/", "/mainpage").permitAll()
                                .anyRequest().authenticated()
                                .and()
                )
                .formLogin()
                .loginPage("/login")
                .successHandler((request, response, authentication) -> response.sendRedirect("/mainpage"))
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

}