package com.example.notificationclient.servers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.notificationclient.entites.Authority;
import com.example.notificationclient.entites.Users;
import com.example.notificationclient.repositorys.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.example.notificationclient.entites.Users appUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No Found"));

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (Authority authority : appUser.getAuthority()) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			grantList.add(grantedAuthority);
		}

		UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
		return user;
	}

	public String findEmailByUsername(String username) {
		Optional<Users> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return user.get().getEmail();
		}
		return null;
	}

}