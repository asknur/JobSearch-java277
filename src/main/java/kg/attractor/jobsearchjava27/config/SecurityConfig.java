package kg.attractor.jobsearchjava27.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery = """
                select email, password, enabled
                from usr
                where email = ?
                """;

        String authQuery = """
                select u.email, r.role
                from usr u
                join user_role ur on u.id = ur.usr_id
                join roles r on ur.role_id = r.id
                where u.email = ?
                """;

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(authQuery)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher("/auth/logout"))
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register").permitAll()

                        .requestMatchers(HttpMethod.GET, "/vacancies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vacancies/category/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/resumes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/resumes/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/vacancies").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PUT, "/vacancies/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE, "/vacancies/**").hasAuthority("USER")

                        .requestMatchers(HttpMethod.POST, "/resumes").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/resumes/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/resumes/**").hasAuthority("ADMIN")

                        .requestMatchers("/users/**").authenticated()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}