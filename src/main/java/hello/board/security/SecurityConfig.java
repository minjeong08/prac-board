package hello.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService);

        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/system/**").permitAll()
                        .requestMatchers("/board/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/system/login")
                        .defaultSuccessUrl("/board/getBoardList", true)
                )
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .accessDeniedPage("/system/accessDenied")
                )
                .logout(logout -> logout
                        .logoutUrl("/system/logout")
                        .logoutSuccessUrl("/system/logout")
                )
                .logout(logout -> logout
                        .logoutUrl("/system/logout")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/system/logout")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}