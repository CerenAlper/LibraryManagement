package infra.issueloader;

import domain.classes.Library;
import domain.classes.LibraryManagement;
import domain.classes.MemberManagement;

public class IssueLoader implements IIssueLoader {
	private LibraryManagement libraryManager;
	private Library[] libraries;
	private MemberManagement memberManager;
	
	public IssueLoader(LibraryManagement libraryManager, Library[] libraries, MemberManagement memberManager) {
		this.libraryManager = libraryManager;
		this.libraries = libraries;
		this.memberManager = memberManager;
	}
	
	public void loadIssues() {
		for (int i = 0; i < libraryManager.getIssues().length; i++) {
			var libraryIssues = libraryManager.getIssues()[i];
			for (int j = 0; j < libraryIssues.length; j++) {
				libraryIssues[j].setBook(libraries[i].findBookById(libraryIssues[j].getBookId()));
				libraryIssues[j].setMember(memberManager.findMemberById(libraryIssues[j].getMemberId()));
			}
		}
	}
}
