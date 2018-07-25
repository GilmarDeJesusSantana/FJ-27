package br.com.caelum.casadocodigo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.caelum.casadocodigo.models.User;

@Repository
public class UserDao {
	@PersistenceContext
	private EntityManager em;

	public User findUser(String username) {
		String jpql = "select u from User u where u.login =:login";
		List<User> users = em.createQuery(jpql, User.class).setParameter("login", username).getResultList();

		if (users.isEmpty()) {
			throw new UsernameNotFoundException("O usuario " + username + " não existe");
		}
		return users.get(0);

	}
}
