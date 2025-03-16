package infra.importer;

import domain.classes.Book;
import domain.classes.Library;
import util.FileIOReader;

public class LibraryImporter implements IImporter<Library> {
	@Override
	public Library importFromCsv(String folderPath, String fileName) throws Exception {
		var rows = FileIOReader.ReadFromCSV(folderPath + fileName);

		var books = new Book[rows.size() - 1];

		// skip first line to escape headers
		for (int j = 1; j < rows.size(); j++) {
			var row = rows.get(j);

			// catch inner error, add file and line information, re-throw.
			try {
				books[j - 1] = mapRowToBook(row);
			} catch (Exception e) {
				throw new Exception(e.getMessage() + fileName + " line: " + j);
			}
		}

		return new Library(books);
	}
	
	// maps row data to book constructor, initializes and returns book
	private Book mapRowToBook(String[] row) throws Exception {

		// validation
		if (row.length != 7) {
			throw new Exception("format error! cannot read book data!");
		}

		// strip leading and trailing quotation marks
		for (int i = 0; i < row.length; i++) {
			row[i] = row[i].replaceAll("(^\")|(\"$)", "");
		}

		// define variables in this scope because we need these for book arguments
		int id;
		int quantity;

		// parse may throw error, handle error and humanize message
		try {
			id = Integer.parseInt(row[0]);
			quantity = Integer.parseInt(row[6]);
		} catch (NumberFormatException e) {
			throw new Exception("number format error! cannot read book data!");
		}

		String title = row[1];
		String author = row[2];
		String publisher = row[3];
		String edition = row[4];
		String genre = row[5];

		return new Book(id, title, author, publisher, edition, genre, quantity);
	}
}
