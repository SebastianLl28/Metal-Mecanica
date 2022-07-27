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
		.antMatchers("/css/**", "/img/**", "/js/**", "/", "/principal", "/home", "/inicio", "/logeo", "/login", "/moduloProducto/**","/rest/**")
		.permitAll()
		
		.antMatchers("/listarProducto").hasAnyRole("ADMIN")
		.antMatchers("/cliente/listarTodo").hasAnyRole("ADMIN","LECTOR","CREADOR","EDITOR","DEPURADOR")
		.antMatchers("/moduloCliente/listarTodo").hasAnyRole("ADMIN","LECTOR","CREADOR","EDITOR","DEPURADOR")
		.antMatchers("/cliente/nuevo").hasAnyRole("ADMIN","CREADOR")
		.antMatchers("/cliente/guardar").hasAnyRole("ADMIN","CREADOR","EDITOR")
		.antMatchers("/cliente/actualizar/**").hasAnyRole("ADMIN","EDITOR")
		.antMatchers("/cliente/eliminar/**").hasAnyRole("ADMIN","DEPURADOR")
		.antMatchers("/producto/listarTodo").hasAnyRole("ADMIN","LECTOR","CREADOR","EDITOR","DEPURADOR")
		.antMatchers("/producto/nuevo").hasAnyRole("ADMIN","CREADOR")
		.antMatchers("/producto/guardar").hasAnyRole("ADMIN","CREADOR","EDITOR")
		.antMatchers("/producto/actualizar/**").hasAnyRole("ADMIN","EDITOR")
		.antMatchers("/producto/eliminar/**").hasAnyRole("ADMIN","DEPURADOR")
		.antMatchers("/trabajador/listarTodo").hasAnyRole("ADMIN","LECTOR","CREADOR","EDITOR","DEPURADOR")
		.antMatchers("/trabajador/nuevo").hasAnyRole("ADMIN","CREADOR")
		.antMatchers("/trabajador/guardar").hasAnyRole("ADMIN","CREADOR","EDITOR")
		.antMatchers("/trabajador/actualizar/**").hasAnyRole("ADMIN","EDITOR")
		.antMatchers("/trabajador/eliminar/**").hasAnyRole("ADMIN","DEPURADOR")
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/bienvenida", true).permitAll()
		.and().logout()
		.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("ADMIN").and()
		.withUser("jorge").password(encoder.encode("jorge")).roles("LECTOR").and()
		.withUser("maria").password(encoder.encode("maria")).roles("CREADOR").and()
		.withUser("elena").password(encoder.encode("elena")).roles("LECTOR","DEPURADOR").and()
		.withUser("ernesto").password(encoder.encode("ernesto")).roles("EDITOR","LECTOR").and();
	}
	
	
}
