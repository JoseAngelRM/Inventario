package com.example.inventario.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests( autz -> autz
                    .requestMatchers("/login","/resources/**", "/webjars/**", "/css/**", "/js/**").permitAll()
                    .requestMatchers("/editar/**","/agregar/**","/eliminar/**","/historial/**", "/guardar/**", "/sumarInventario/**", "/editarSuma/**").hasRole("ADMINISTRADOR")
                    .requestMatchers("/restaInventario/**", "/editarResta/**").hasRole("ALMACENISTA")
                    .requestMatchers("/").hasAnyRole("ALMACENISTA","ADMINISTRADOR")
                    .anyRequest()
                    .authenticated()
            ).formLogin( formL -> formL
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout( logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                )
                 .exceptionHandling( exceptionHand -> exceptionHand
                    .accessDeniedPage("/errores/403")
                );
        
        return http.build();
    }
    
}
