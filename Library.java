package domain.classes;

import java.util.HashMap;
import java.util.Map;

public class Library {
	private Book[] books;
	private Map<Integer, Integer> index;

	public Library(Book[] books) {
		super();
		this.books = books;
		this.indexBooks();
	}

	public Book[] getBooks() {
		return books;
	}

	// we indexed book id's and array indexes on initialization, that way it is just
	// fast index lookup.
	// if it is still not found search all books in array.
	public Book findBookById(int id) {
		if (index.containsKey(id)) {
			return books[index.get(id)];
		}

		//// more primitive solution to finding book, assumes index = id - 1. which may
		//// not always be the case
//		if (books[id - 1].getId() == id) {
//			return books[id - 1];
//		}

		return findBookInLibraryBF(id);
	}

	private Book findBookInLibraryBF(int id) {
		for (Book book : books) {
			if (book.getId() == id)
				return book;
		}
		return null;
	}

	private void indexBooks() {
		index = new HashMap<Integer, Integer>();
		for (int i = 0; i < books.length; i++) {
			index.put(books[i].getId(), i);
		}
	}
}
