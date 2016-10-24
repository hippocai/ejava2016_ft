package ejava.wk3.business;

import ejava.wk3.model.Customer;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CustomerBean {

	@PersistenceContext private EntityManager em;

	public Optional<Customer> get(Integer cid) {
		return (Optional.ofNullable(
				em.find(Customer.class, cid)
		));
	}

	public List<Customer> getAllCustomers(Integer start, Integer size) {
		TypedQuery<Customer> query = em.createNamedQuery(
				"Customer.findAll", Customer.class);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return (query.getResultList());
	}

	public List<Customer> getAllCustomers() {
		TypedQuery<Customer> query = em.createNamedQuery(
				"Customer.findAll", Customer.class);
		return (query.getResultList());
	}
	
}
