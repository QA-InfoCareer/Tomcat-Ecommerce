package com.myshopping.myshopping.utility;

/*@Configuration
@EnableWebSecurity*/
public class Configure {
	
	/*
	 * 
	 * @Bean public UserDetailsService config(PasswordEncoder encoder) { UserDetails
	 * admin=User.withUsername("Naveen") .password(encoder.encode("naveen123"))
	 * .roles("ADMIN") .build(); return new InMemoryUserDetailsManager(admin); }
	 * public SecurityFilterChain securityFilterChain(HttpSecurity http)throws
	 * Exception { return http.csrf().disable() .authorizeHttpRequests()
	 * .requestMatchers("/admin/**","/soldproduct/**","/product/**","/myorders/**",
	 * "/mailvalidation/**","/cart/**","/billCopy/**","/bill/**").hasRole("ADMIN")
	 * .and().authorizeHttpRequests() .and().formLogin().loginPage("/Login.html")
	 * .and().build();
	 * 
	 * 
	 * }
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
}
