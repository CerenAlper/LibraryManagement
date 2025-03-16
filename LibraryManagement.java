package domain.classes;

public class LibraryManagement {
	private Issue[][] issues;

	public LibraryManagement(Issue[][] issues) {
		super();
		this.issues = issues;
	}

	public Issue[][] getIssues() {
		return issues;
	}
}
