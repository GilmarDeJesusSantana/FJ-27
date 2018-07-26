package br.com.caelum.casadocodigo.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.caelum.casadocodigo.daos.UserDao;
import br.com.caelum.casadocodigo.services.UserServices;

@EnableWebMvcSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserServices userService;
	
	@Autowired
	private UserDao users;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
				.antMatchers("/products/form").hasRole("ADMIN")
				.antMatchers("/products", "/products/**").permitAll()
				.antMatchers("/shopping/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/products").permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/loggout"))
				.logoutSuccessUrl("/products")
				.permitAll()
				.and()
				.exceptionHandling()
				.accessDeniedPage("/WEB-INF/views/errors/403.jsp");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		System.out.println("add public resources files");
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());;
	}
}
