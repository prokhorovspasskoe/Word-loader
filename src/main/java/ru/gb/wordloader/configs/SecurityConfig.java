package ru.gb.wordloader.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.gb.wordloader.security.jwt.JwtConfigurer;
import ru.gb.wordloader.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    private JwtTokenProvider jwtTokenProvider;
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private static final String SIGNUP_ENDPOINT = "/api/v1/registration/signup";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String SWAGGER_DOC = "/v3/api-docs/**";

    @Autowired
    public void SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(SIGNUP_ENDPOINT, LOGIN_ENDPOINT, SWAGGER_UI, SWAGGER_DOC).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}