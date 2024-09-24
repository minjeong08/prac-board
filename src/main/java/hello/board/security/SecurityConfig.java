package hello.board.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfiguration {

    private final SecurityUserDetailsService userDetailsService;

    protected void configure(HttpSecurity security) throws Exception {
        security.userDetailsService(userDetailsService);

        security.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/", "/system/**").permitAll()
        );

        security.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/board/**").authenticated()
        );

        security.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
        );

        security.csrf(AbstractHttpConfigurer::disable);
        security.oauth2Login(oauth2 -> oauth2
                .loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true));
        security.exceptionHandling((exceptions) -> exceptions.accessDeniedPage("/system/accessDenied"));
        security.logout((logout) -> logout.logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/"));
    }
}
