package com.example.minimarket2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/producto").permitAll()
				.antMatchers("/producto/nuevo").hasRole("ADMIN")
				.antMatchers("/producto/info/**").permitAll()
				.antMatchers("/producto/edit/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/producto/del/**").hasRole("ADMIN")
				.antMatchers("/categoria").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/categoria/info/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/categoria/nuevo").hasRole("ADMIN")
				.antMatchers("/categoria/edit/**").hasRole("ADMIN")
				.antMatchers("/categoria/del/**").hasRole("ADMIN")
				.antMatchers("/proveedor").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/proveedor/info/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/proveedor/nuevo").hasRole("ADMIN")
				.antMatchers("/proveedor/edit/**").hasRole("ADMIN")
				.antMatchers("/proveedor/del/**").hasRole("ADMIN")
				.antMatchers("/cargo").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/cargo/info/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/cargo/edit/**").hasRole("ADMIN")
				.antMatchers("/cargo/del/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginProcessingUrl("/signin")
				.loginPage("/login").permitAll()
				.usernameParameter("inputUsername")
                .passwordParameter("inputPassword")
			.and()
	        .logout()
	        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        	.logoutSuccessUrl("/")
	        .and()
            .rememberMe()
            	.tokenValiditySeconds(2592000)
            	.key("Cl4v3.")
            	.rememberMeParameter("checkRememberMe")
            	.userDetailsService(usuarioDetailsService)
            .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler);
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.usuarioDetailsService);

        return daoAuthenticationProvider;
    }
	
}
