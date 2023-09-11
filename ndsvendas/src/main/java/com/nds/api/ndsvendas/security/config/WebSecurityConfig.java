package com.nds.api.ndsvendas.security.config;

import com.nds.api.ndsvendas.security.jwt.JwtAuthenticationTokenFilter;
import com.nds.api.ndsvendas.security.service.impl.UserDetailsServiceImpl;
import com.nds.api.ndsvendas.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private   UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	@Bean
	public OncePerRequestFilter jwtAuthenticationTokenFilter(){
		return new JwtAuthenticationTokenFilter(this.userDetailsServiceImpl, this.jwtUtil);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/configuracoes/**").permitAll()
				//.antMatchers("/utilizadores/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				;
	}


}