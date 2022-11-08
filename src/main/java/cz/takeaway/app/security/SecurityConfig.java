package cz.takeaway.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import cz.takeaway.app.enumetation.RoleEnum;
import cz.takeaway.app.security.handlers.SuccessLoginHandler;
import cz.takeaway.app.service.UserSecurityDetailsService;

@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserSecurityDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationSuccessHandler loginHandler() {
		return new SuccessLoginHandler();
	}

	@Bean
	public AuthenticationFailureHandler failHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.cors().and().authenticationProvider(authenticationProvider()).authorizeHttpRequests()
				.antMatchers("/takeaway", "/takeaway/v1/**").permitAll().antMatchers("/admin/**").hasAnyAuthority(RoleEnum.USER.getAuthority())
				.and().formLogin().loginPage("/#signIn").loginProcessingUrl("/login").and().logout().deleteCookies("JSESSIONID").and().csrf().disable()
				.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.debug(false).ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**",
				"/favicon.ico");
	}
}
