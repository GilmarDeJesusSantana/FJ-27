package br.com.caelum.casadocodigo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.caelum.casadocodigo.daos.UserDao;
import br.com.caelum.casadocodigo.models.User;

@Service
public class UserServices implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findUser(username) ;		
		
		return Optional.of(user).orElseThrow(() -> new UsernameNotFoundException("Qualquer coisa"));
	}
	

}
