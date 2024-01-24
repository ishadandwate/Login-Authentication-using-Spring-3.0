package com.LoginModule.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.LoginModule.Entity.User;
import com.LoginModule.Entity.UserInfoDetails;
import com.LoginModule.Repo.UserRepository;
import com.LoginModule.Utils.JwtHelper;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtHelper helper;

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> userDetail = userRepository.findByEmail(email);

		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	private String getEmailForUserId(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		return userOptional.map(User::getEmail).orElse(null);
	}

}
