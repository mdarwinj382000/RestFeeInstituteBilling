package spring.restproject.restfeeinstitutebilling.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.restproject.restfeeinstitutebilling.entities.Usercollection;
import spring.restproject.restfeeinstitutebilling.repositories.UserCollectionrepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	UserCollectionrepository userCollectionrepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	Usercollection usercollection;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.equals("ROOT")) {
			return User.withUsername(username).password(bCryptPasswordEncoder.encode("root")).authorities("ROOT")
					.build();
		} else {
			getUsercollection(username);
			return User.withUsername(usercollection.getUsername()).password(usercollection.getPassword())
					.authorities(usercollection.getClass().getSimpleName().toUpperCase()).build();
		}
	}

	public void updateUserPassword(String username, String password) {
		getUsercollection(username);
		usercollection.setPassword(password);
		userCollectionrepository.save(usercollection);
	}

	public void getUsercollection(String username) {
		usercollection = null;
		if (username.matches("^((A[DC])|ST){1}[0-9]*$")) {
			usercollection = userCollectionrepository.findById(username).orElse(null);
		} else {
			usercollection = userCollectionrepository.findByEmail(username).orElse(null);
		}
		if (usercollection == null)
			throw new UsernameNotFoundException("User Not Found");
	}

}
