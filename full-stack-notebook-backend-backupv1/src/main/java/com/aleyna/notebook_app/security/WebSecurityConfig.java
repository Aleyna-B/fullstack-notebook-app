package com.aleyna.notebook_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.aleyna.notebook_app.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
    UserDetailsService userDetailsService() {
		//DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        return new CustomUserDetailsService();
    }
 
	
//	  @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();
//	  }
	 
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        //authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
//    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//    authProvider.setUserDetailsService(userDetailsService());
// 
    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            //response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("success");
        };
    }

   @Bean
   public AuthenticationFailureHandler customFailureHandler() {
       return (request, response, exception) -> {
           //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.getWriter().write("fail");
       };
   }
    
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        
        http.authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests(auth ->
		auth .requestMatchers("/user").authenticated() 
            .anyRequest().permitAll()
            )
        .csrf(csrf -> csrf.disable())
            .formLogin(login ->
                login.usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/user")
                .permitAll()
                .successHandler(customSuccessHandler())
                .failureHandler(customFailureHandler())
            )
            .logout(logout -> logout.logoutSuccessUrl("/").permitAll() //http://localhost:8080/v1/notebook/logout urıında
        );
         
        return http.build();
    }  
}
