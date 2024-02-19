package sn.esmt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurtyConfig
{
	//@Autowired
    //private UserDetailsServiceImp userDetailsServiceImp;

	
	// gestion des authorisations
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
	     http.authorizeHttpRequests()
		  //   .requestMatchers("/").permitAll()
	           .requestMatchers("/").authenticated()
	           .requestMatchers("/gescateg").hasAnyRole("ADMIN","USER")
	           .requestMatchers("/selectcateg").hasAnyRole("USER","ADMIN")
	           .requestMatchers("/gesprod").hasAnyRole("USER","ADMIN")
	           .requestMatchers("/enregistreprod").hasAnyRole("USER","ADMIN")
	           .requestMatchers("/listebycateg").hasAnyRole("USER","ADMIN")
	           .requestMatchers("/creercateg").hasRole("ADMIN")
	           .requestMatchers("/enregistrecateg").hasRole("ADMIN")
	           .requestMatchers("/supcateg/**").hasRole("ADMIN")
	           .requestMatchers("/editercateg/**").hasRole("ADMIN")
	           .requestMatchers("/modifiecateg").hasRole("ADMIN")
	           .requestMatchers("/supprod/**").hasRole("ADMIN")
	           .requestMatchers("/editerprod/**").hasRole("ADMIN")
	           .requestMatchers("/modifprod").hasRole("ADMIN")
	           // toute requête no définie dans cette liste et authentifiée
	           .anyRequest().authenticated()
	           .and()
	           // gestion du Login
	           .formLogin()
	           .defaultSuccessUrl("/gescateg",true)
	           // gestion du Logout
	           .and()
	           .logout()
	           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	           // gestion d'une page d'erreur
	           .and()
	           .exceptionHandling()
	           .accessDeniedPage("/error");
	     
	            return http.build();
	            
	}
	
	// méthode d'encodage des mots de passe
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
