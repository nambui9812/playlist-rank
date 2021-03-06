package nambui9812.playlistrank.jwt;

import org.springframework.http.HttpMethod;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Autowired
  private UserDetailsService jwtUserDetailsService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // Configure AuthenticationManager so that it knows from where to load
    // user for matching credentials
    // Use BCryptPasswordEncoder
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // Enable cors and disable csrf
    httpSecurity
    .cors().and().csrf().disable()
    // Allow these requests
    .authorizeRequests()
    .antMatchers(HttpMethod.GET ,"/users/**").permitAll()
    .antMatchers(HttpMethod.POST ,"/users/sign-up/**").permitAll()
    .antMatchers(HttpMethod.POST, "/users/sign-in/**").permitAll()
    .antMatchers(HttpMethod.GET, "/playlists/**").permitAll()
    .antMatchers(HttpMethod.GET, "/tags/**").permitAll()
    .antMatchers(HttpMethod.GET, "/comments/**").permitAll()
    // All other requests need to be authenticated
    .anyRequest().authenticated().and()
    // Make sure we use stateless session; session won't be used to
    // store user's state.
    .exceptionHandling()
    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    
    // Add a filter to validate the tokens with every request
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
