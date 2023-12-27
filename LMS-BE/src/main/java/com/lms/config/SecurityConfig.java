package com.lms.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtFilterConfig jfl;

	@Bean
	SecurityFilterChain sfc(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());
		http.cors(cor -> cor.configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				List<String> listoforigin = List.of("*");
				List<String> listofmethods = List.of("GET", "POST", "PUT", "DELETE");
				List<String> listofheaders = List.of("*");
				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOrigins(listoforigin);
				cfg.setAllowedMethods(listofmethods);
				cfg.setAllowedHeaders(listofheaders);
				// cfg.setAllowCredentials(true);
				return cfg;
			}
		}));

		http.authorizeHttpRequests(

				auth ->

				{
					auth.requestMatchers("/getallapi", "/admin/delete/{email}","/admin/removecourseaccess","/admin/getallcourses").permitAll();

					auth.requestMatchers("/admin/signup", "/admin/importusers", "/admin/update").authenticated();

					auth.requestMatchers("/auth/login").permitAll();

					auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/webjars/**",
							"/swagger-resources/**").permitAll();

					auth.requestMatchers("/user/connect", "user/login", "/user/uploadimage", "/user/download/{email}",
							"/user/update", "/user/getotp", "/user/verifyacc", "/user/resetpassword").permitAll();

					auth.requestMatchers("/course/addcourseuser", "/course/addcourse", "/course/accesscoursetouser",
							"/course/addvideolink", "/course/getcourseusers", "/course/getcourse", "/course/getvideos",
							"/course/deletecourse").permitAll();

				}).sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(ap()).addFilterBefore((Filter) jfl, UsernamePasswordAuthenticationFilter.class);

		;
		http.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	UserDetailsService uds() {
		return new UserUserdetailsService();
	}

	@Bean
	AuthenticationProvider ap() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setUserDetailsService(uds());
		dap.setPasswordEncoder(pe());

		return dap;
	}

	@Bean
	PasswordEncoder pe() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager am(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();
	}

}
