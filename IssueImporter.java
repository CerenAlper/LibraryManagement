package infra.importer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import domain.classes.Issue;
import util.FileIOReader;

public class IssueImporter implements IImporter<Issue[]> {
	@Override
	public Issue[] importFromCsv(String folderPath, String fileName) throws Exception {
		var rows = FileIOReader.ReadFromCSV(folderPath + fileName);

		var issues = new Issue[rows.size() - 1];

		for (int j = 1; j < rows.size(); j++) {
			var row = rows.get(j);
			try {
				issues[j - 1] = mapRowToIssue(row);
			} catch (Exception e) {
				throw new Exception(e.getMessage() + fileName + " line: " + j);
			}
		}
		return issues;
	}
	
	private Issue mapRowToIssue(String[] row) throws Exception {

		// validation
		if (row.length != 5) {
			throw new Exception("format error! cannot read issue data!");
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		int id;
		int memberId;
		int bookId;
		Date issueDate;
		Date returningDate;
		try {
			id = Integer.parseInt(row[0]);
			memberId = Integer.parseInt(row[1]);
			bookId = Integer.parseInt(row[2]);
			issueDate = formatter.parse(row[3]);
			returningDate = formatter.parse(row[4]);
		} catch (NumberFormatException e) {
			throw new Exception("number format error! cannot read book data!");
		} catch (ParseException e) {
			throw new Exception("date format error! cannot read issue data!");
		}
		
		return new Issue(id, memberId, bookId, issueDate, returningDate);
	}
}
