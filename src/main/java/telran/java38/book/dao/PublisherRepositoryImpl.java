package telran.java38.book.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
