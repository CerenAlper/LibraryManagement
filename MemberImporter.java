package infra.importer;

import domain.classes.Member;
import domain.classes.MemberManagement;
import util.FileIOReader;

public class MemberImporter implements IImporter<MemberManagement> {
	@Override
	public MemberManagement importFromCsv(String folderPath, String fileName) throws Exception {
		var rows = FileIOReader.ReadFromCSV(folderPath + fileName);
		
		var members = new Member[rows.size() - 1];
		
		for (int i = 1; i < rows.size(); i++) {
			var row = rows.get(i);
			try {
				members[i - 1] = mapRowToMember(row);
			} catch (Exception e) {
				throw new Exception(e.getMessage() + fileName + " line: " + i);
			}
		}
		
		return new MemberManagement(members);
	}
	
	private Member mapRowToMember(String[] row) throws Exception {
		
		if (row.length != 3)
			throw new Exception("format error! cannot read member data!");
		
		int id;
		
		try {
			id = Integer.parseInt(row[0]);
		} catch (Exception e) {
			throw new Exception("number format error! cannot read book data!");
		}
		
		String name = row[1];
		String email = row[2];
		
		return new Member(id, name, email);
	}
}
