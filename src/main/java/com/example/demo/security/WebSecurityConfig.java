package com.example.demo.security;

import com.example.demo.services.Impl.ClientServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ClientServiceImpl userService;

    public WebSecurityConfig(ClientServiceImpl userService) {
        this.userService = userService;
    }

    // TODO: 05.05.2022 переделать как у себя в паттернах
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/client/home").permitAll()//стартовая страница
                .antMatchers("/logout","/room/name","/client/"
                        ,"/client/newClientDoc"
                        ,"/client/createClientDoc"
                        ,"/client/newRoomContract"
                        ,"/client/rooms"
                        ,"/client/createClientRoom"
                        ,"/client/myRoomList"
                        ,"/client/delRoom/{id}"
                        ,"/client/editRoom/{id}"
                        ,"/client/updateRoom/{id}",
                        "/contractMaintenance/myList").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/card/*","/client/*",
                        "/food/*","/cleaning/*","/maintenance/*",
                        "/contractMaintenance/*","/room/*")
                    .hasAnyAuthority("ADMIN")
                .antMatchers( "/sign").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login-error").defaultSuccessUrl("/client/home").permitAll().
                and()
                .logout().logoutSuccessUrl("/client/home").
                and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession();
    }
}
