package com.umadev.springboot.mvc.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        // Add support for JDBC, to not hardcode users. This code doesn't change with
        // pwd encryption
        @Bean
        UserDetailsManager userDetailsManager(DataSource dataSource) {

                // Queries for using custom tables/schema
                JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

                // Define query to retrieve a user by username
                jdbcUserDetailsManager.setUsersByUsernameQuery(
                                // Question mark is the parameter value from login
                                "select user_id, pw, active from members where user_id=?");

                // Define query to retrieve the authorities/roles
                jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                                // Question mark is the parameter value from login
                                "select user_id, role from roles where user_id=?");

                return jdbcUserDetailsManager;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http.authorizeHttpRequests(configurer -> configurer
                                // .requestMatchers(HttpMethod.GET, "/showMyLoginPage").permitAll()
                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                // Need a controller
                                                .loginPage("/showMyLoginPage")
                                                // Login form should POST data to this URL for processing
                                                // Does not need a controller
                                                .loginProcessingUrl("/authenticateTheUser")
                                                // Allow everyone to see the page
                                                .permitAll())

                                .logout(logout -> logout.permitAll()

                                );

                return http.build();
        }

}
