package telran.java38.book.dao;

import java.util.Optional;

import telran.java38.book.model.Publisher;

public interface PublisherRepository {
	Optional<Publisher> findById(String id);

	Publisher save(Publisher publisher);
}
