package telran.java38.book.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169454254285092134L;

}
