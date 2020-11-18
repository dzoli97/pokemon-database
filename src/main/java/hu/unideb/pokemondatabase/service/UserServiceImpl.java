package hu.unideb.pokemondatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.unideb.pokemondatabase.entity.User;
import hu.unideb.pokemondatabase.repo.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public String registerUser(User user) {
		if (!user.getPassword().equals(user.getMatchingPassword())) {
			return "PassNotMatch";
		}

		User checkedUser = userRepository.findByUsername(user.getUsername());
		if (checkedUser == null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return "OK";
		} else {
			return "UserExists";
		}

	}

}