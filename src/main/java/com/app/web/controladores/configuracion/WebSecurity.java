package com.app.web.controladores.configuracion;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/css/**", "/img/**", "/js/**", "/", "/principal", "/home", "/inicio", "/logeo", "/login","/rest/**","/registro")
		.permitAll()
		.antMatchers("/producto/listarTodo").hasAnyRole("ADMIN", "ALMACEN", "VENDEDOR")
		.antMatchers("/producto/nuevo").hasAnyRole("ADMIN", "ALMACEN")
		.antMatchers("/producto/actualizar/**").hasAnyRole("ADMIN", "ALMACEN")
		.antMatchers("/producto/eliminar/**").hasAnyRole("ADMIN", "ALMACEN")
		.antMatchers("/trabajador/listarTodo").hasAnyRole("ADMIN", "ALMACEN")
		.antMatchers("/trabajador/nuevo").hasAnyRole("ADMIN")
		.antMatchers("/trabajador/actualizar/**").hasAnyRole("ADMIN")
		.antMatchers("/trabajador/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/cliente/listarTodo").hasAnyRole("ADMIN", "VENDEDOR")
		.antMatchers("/cliente/nuevo").hasAnyRole("ADMIN", "VENDEDOR")
		.antMatchers("/cliente/actualizar/**").hasAnyRole("ADMIN", "VENDEDOR")
		.antMatchers("/cliente/eliminar/**").hasAnyRole("ADMIN", "VENDEDOR")
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/bienvenida", true).permitAll()
		.and().logout()
		.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("ADMIN").and()
		.withUser("cliente").password(encoder.encode("cliente")).roles("CLIENTE").and()
		.withUser("vendedor").password(encoder.encode("vendedor")).roles("VENDEDOR").and()
		.withUser("almacen").password(encoder.encode("almacen")).roles("ALMACEN").and()
		.withUser("ernesto").password(encoder.encode("ernesto")).roles("EDITOR","LECTOR").and();
	}
	
	
}
