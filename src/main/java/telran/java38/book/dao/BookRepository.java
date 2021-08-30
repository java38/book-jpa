package telran.java38.book.dao;

import java.util.Optional;

import telran.java38.book.model.Book;

public interface BookRepository {
	Optional<Book> findById(String id);

	Book save(Book book);

	void delete(Book book);
	
	boolean existsById(String id);
}
