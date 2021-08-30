package telran.java38.book.service;

import telran.java38.book.dto.AuthorDto;
import telran.java38.book.dto.BookDto;

public interface BookService {
	boolean addBook(BookDto bookDto);

	BookDto findBookByIsbn(String isbn);
	
	BookDto removeBook(String isbn);
	
	BookDto updateBook(String isbn, String title);
	
	AuthorDto removeAuthor(String authorName);
	
	Iterable<BookDto> findBooksByAuthor(String authorName);
	
	Iterable<BookDto> findBooksByPublisher(String publisherName);
	
	Iterable<AuthorDto> findBookAuthors(String isbn);
	
	Iterable<String> findPublishersByAuthor(String authorName);
}
