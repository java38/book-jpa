package telran.java38.book.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.java38.book.dao.AuthorRepository;
import telran.java38.book.dao.BookRepository;
import telran.java38.book.dao.PublisherRepository;
import telran.java38.book.dto.AuthorDto;
import telran.java38.book.dto.BookDto;
import telran.java38.book.dto.exceptions.DocumentNotFoundException;
import telran.java38.book.model.Author;
import telran.java38.book.model.Book;
import telran.java38.book.model.Publisher;

@Service
public class BookServiceImpl implements BookService {

	BookRepository bookRepository;
	AuthorRepository authorRepository;
	PublisherRepository publisherRepository;
	ModelMapper modelMapper;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
			PublisherRepository publisherRepository, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.publisherRepository = publisherRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional
	@Override
	public boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		//Publisher
		String publisherName = bookDto.getPublisher();
		Publisher publisher = publisherRepository.findById(publisherName)
				.orElseGet(() -> publisherRepository.save(new Publisher(publisherName)));
		//Authors
		Set<Author> authors = bookDto.getAuthors()
				.stream()
				.map(a -> authorRepository.findById(a.getName()).orElseGet(() -> authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(() -> new DocumentNotFoundException());
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	public BookDto removeBook(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto updateBook(String isbn, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthorDto removeAuthor(String authorName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BookDto> findBooksByAuthor(String authorName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BookDto> findBooksByPublisher(String publisherName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<AuthorDto> findBookAuthors(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> findPublishersByAuthor(String authorName) {
		// TODO Auto-generated method stub
		return null;
	}

}
