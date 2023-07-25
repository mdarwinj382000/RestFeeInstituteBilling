package spring.restproject.restfeeinstitutebilling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import spring.restproject.restfeeinstitutebilling.entities.UserRole;
import spring.restproject.restfeeinstitutebilling.exceptionhandling.CustomAccessDeniedHandler;
import spring.restproject.restfeeinstitutebilling.security.AuthEntryPointJwt;
import spring.restproject.restfeeinstitutebilling.security.AuthTokenFilter;

@Configuration
public class SecurityConfiguration {

	@Bean
	public BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
			AuthTokenFilter authTokenFilter, AuthEntryPointJwt authEntryPointJwt,
			CustomAccessDeniedHandler accessDeniedHandler) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/signin").permitAll().antMatchers("/admin/get")
				.hasAnyAuthority(UserRole.ADMIN.toString(), UserRole.ROOT.toString())
				.antMatchers("/accountant/get", "/student/all")
				.hasAnyAuthority(UserRole.ADMIN.toString(), UserRole.ACCOUNTANT.toString())
				.antMatchers("/student/getpaymenthistory", "/student/getpayment", "/student/getpaymentwithsemester","/student/get")
				.hasAnyAuthority(UserRole.STUDENT.toString(), UserRole.ACCOUNTANT.toString())
				.antMatchers("/student/updatepayment").hasAuthority(UserRole.STUDENT.toString()).antMatchers("/admin/**")
				.hasAuthority(UserRole.ROOT.toString()).antMatchers("/accountant/**")
				.hasAuthority(UserRole.ADMIN.toString()).antMatchers("/student/**")
				.hasAuthority(UserRole.ACCOUNTANT.toString()).anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
				.authenticationEntryPoint(authEntryPointJwt).accessDeniedHandler(accessDeniedHandler);
		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationprovider(BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		return provider;
	}
}
