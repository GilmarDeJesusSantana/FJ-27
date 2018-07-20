package br.com.caelum.casadocodigo.daos;

import br.com.caelum.casadocodigo.models.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductDao {

	@PersistenceContext
	private EntityManager em;

	public Product getById(Integer id) {
		return em.createQuery("select distinct(p) from Product p join fetch p.prices where p.id = : id", Product.class)
				.setParameter("id", id).getSingleResult();
	}

	public void save(Product product) {
		em.persist(product);
	}

	public List<Product> listAll() {
		return em.createQuery("select distinct(p) from Product p join fetch p.prices", Product.class).getResultList();
	}
}
