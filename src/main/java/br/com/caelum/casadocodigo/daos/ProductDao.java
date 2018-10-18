package br.com.caelum.casadocodigo.daos;

import br.com.caelum.casadocodigo.models.BookType;
import br.com.caelum.casadocodigo.models.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductDao {

	@PersistenceContext
	private EntityManager em;

	public Product getById(Long id) {
		return em.createQuery("select distinct(p) from Product p join fetch p.prices where p.id=:id", Product.class)
				.setParameter("id", id).getSingleResult();
	}
	
	public Product getFindById(Integer id) {
		return em.createQuery("select distinct(p) from Product p join fetch p.prices where p.id=:id", Product.class)
				.setParameter("id", id).getSingleResult();
	}

	public void save(Product product) {
		em.persist(product);
	}

	public List<Product> list() {
		return em.createQuery("select distinct(p) from Product p join fetch p.prices",Product.class).getResultList();
	}

	public BigDecimal sumPricesPerType(BookType bookType) {
        TypedQuery<BigDecimal> query = em.createQuery("select sum(price.price) from Product p join p.prices price where price.bookType =:bookType",BigDecimal.class);
        query.setParameter("bookType", bookType);
        return query.getSingleResult();
	}
}
