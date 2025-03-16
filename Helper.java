package util;
import domain.classes.*;
import infra.importer.IImporter;
import infra.importer.IssueImporter;
import infra.importer.LibraryImporter;
import infra.importer.MemberImporter;

public class Helper {
	// imports and initializes libraries from given csv paths then returns libraries
	public static Library[] importLibraryBooksCsvs(String folderPath, String[] libraryPaths) throws Exception {
		var libraries = new Library[libraryPaths.length];
		IImporter<Library> libraryImporter = new LibraryImporter();

		for (int i = 0; i < libraryPaths.length; i++) {
			libraries[i] = libraryImporter.importFromCsv(folderPath, libraryPaths[i]);
		}

		return libraries;
	}

	public static LibraryManagement importLibraryIssuesCsvs(String folderPath, String[] issuesPaths) throws Exception {
		var libraryIssues = new Issue[issuesPaths.length][];
		IImporter<Issue[]> issueImporter = new IssueImporter();

		for (int i = 0; i < issuesPaths.length; i++) {
			libraryIssues[i] = issueImporter.importFromCsv(folderPath, issuesPaths[i]);
		}
		return new LibraryManagement(libraryIssues);
	}
	
	public static MemberManagement importMemberCsv(String folderPath, String membersPath) throws Exception{
		
		IImporter<MemberManagement> memberImporter = new MemberImporter();
		
		return memberImporter.importFromCsv(folderPath, membersPath);
	}
}
