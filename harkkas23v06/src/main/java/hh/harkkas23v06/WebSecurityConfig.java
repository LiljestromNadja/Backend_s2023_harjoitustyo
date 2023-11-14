package hh.harkkas23v06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import hh.harkkas23v06.web.UserDetailServiceImpl;

@Configuration
//@EnableWebSecurity  // pystyy poistamaan selaimessa localhost:8080/delete/1
@EnableMethodSecurity(securedEnabled = true) //tämä käyttöön harjoitustyössä, ei pysty poistamaan localhost:8080/delete/1 ellei ole admin
public class WebSecurityConfig {
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    
    private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
            new AntPathRequestMatcher("/api/**"), //Spring rest
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher("/articlelist/**"),
            new AntPathRequestMatcher("/css/**"),
            new AntPathRequestMatcher("/articlesjson/**"), //api otettu käyttöön 12.11
            new AntPathRequestMatcher("/commentsjson/**"), //api otettu käyttöön 12.11
            new AntPathRequestMatcher("/appusersjson/**"), //api otettu käyttöön 12.11
            new AntPathRequestMatcher("/adtypesjson/**"), //api otettu käyttöön 12.11
            new AntPathRequestMatcher("/memosjson/**"),
            
            
    };
	
	private static final AntPathRequestMatcher[] ADMIN_LIST_URLS = {
            new AntPathRequestMatcher("/admin/**"), //käyttäjien hallinta
            
    };
	
    @Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
    	http.authorizeHttpRequests().requestMatchers(ADMIN_LIST_URLS).hasAuthority("ADMIN")
		.and()
		.authorizeHttpRequests().requestMatchers(WHITE_LIST_URLS).permitAll()
		.and()
		.authorizeHttpRequests().anyRequest().authenticated()
		//for h2 console
		.and()
		.headers().frameOptions().disable()			
		.and()
		.formLogin().defaultSuccessUrl("/index", true) //.formLogin().defaultSuccessUrl("/articlelist", true) //minne mennään jos kirjautuminen onnistuu
		.and()
		.logout().permitAll();
		
		http.cors().and().csrf().disable();
		
		
		//.addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class); 
		
		return http.build();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

