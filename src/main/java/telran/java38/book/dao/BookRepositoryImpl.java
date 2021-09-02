package telran.java38.book.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import telran.java38.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Optional<Book> findById(String id) {
		return Optional.ofNullable(em.find(Book.class, id));
	}

	@Override
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public void delete(Book book) {
		em.remove(book);

	}

	@Override
	public boolean existsById(String id) {
		return em.find(Book.class, id) != null;
	}

	@Override
	public long deleteByAuthorName(String authorName) {
		TypedQuery<String> queryBooks = em.createQuery("select b.isbn from Book b join b.authors a where a.name=?1", String.class);
		queryBooks.setParameter(1, authorName);
		List<String> booksIsbn = queryBooks.getResultList();
		Query query = em.createQuery("delete from Book b where b.isbn in ?1");
		query.setParameter(1, booksIsbn);
		return query.executeUpdate();
	}

	@Override
	public List<Book> findBooksByAuthor(String authorName) {
		TypedQuery<Book> queryBooks = em.createQuery("select b from Book b join b.authors a where a.name=?1", Book.class);
		queryBooks.setParameter(1, authorName);
		return queryBooks.getResultList();
	}

	@Override
	public List<Book> findBooksByPublisher(String publisherName) {
		TypedQuery<Book> queryBooks = em.createQuery("select b from Book b join b.publisher p where p.publisherName=?1", Book.class);
		queryBooks.setParameter(1, publisherName);
		return queryBooks.getResultList();
	}

}
