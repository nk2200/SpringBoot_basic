package com.example.myapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf((csrfConfig) -> csrfConfig.disable());
		http.formLogin(formLogin -> formLogin.loginPage("/member/login")
												.usernameParameter("userid")
												.defaultSuccessUrl("/"))
			.logout(logout -> logout.logoutUrl("/member/logout")
									.logoutSuccessUrl("/member/login")
									.invalidateHttpSession(true));
		http.authorizeHttpRequests((AuthorizeHttpRequests) -> AuthorizeHttpRequests.requestMatchers("/file/**").hasRole("ADMIN")
																					.requestMatchers("/board/**").hasAnyRole("USER","ADMIN")
																					.requestMatchers("/**","/css/**","/js/**","/images/**").permitAll()
																					.requestMatchers("/member/insert","/member/login").permitAll());
		return http.build();
	}
	
//	@Bean
//	@ConditionalOnMissingBean(UserDetailsService.class)
//	InMemoryUserDetailsManager userDetailsService() {
//		return new InMemoryUserDetailsManager(
//				User.withUsername("foo").password("{noop}demo").roles("ADMIN").build(),
//				User.withUsername("bar").password("{noop}demo").roles("USER").build(),
//				User.withUsername("ted").password("{noop}demo").roles("USER","ADMIN").build()
//				);
//	}
	
	//비밀번호 인코더 빈 추가
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
