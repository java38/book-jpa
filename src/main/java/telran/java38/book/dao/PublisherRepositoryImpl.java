package telran.java38.book.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import telran.java38.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Optional<Publisher> findById(String id) {
		return Optional.ofNullable(em.find(Publisher.class, id));
	}

	@Override
	public Publisher save(Publisher publisher) {
		em.persist(publisher);
		return publisher;
	}

	@Override
	public List<Publisher> findPublishersByAuthor(String authorName) {
		TypedQuery<Publisher> query = em.createQuery(
				"select distinct p from Book b join b.authors a join b.publisher p where a.name=?1", Publisher.class);
		query.setParameter(1, authorName);
		return query.getResultList();
	}

}
