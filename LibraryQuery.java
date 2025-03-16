package domain.services.querymanager;

import domain.classes.Book;
import domain.classes.Library;
import domain.classes.LibraryManagement;
import domain.classes.Member;
import domain.classes.MemberManagement;

// this class manages queries
public class LibraryQuery implements ILibraryQuery {

	private LibraryManagement libraryManager;
	private Library[] libraries;
	private MemberManagement memberManager;

	public LibraryQuery(LibraryManagement libraryManagement, Library[] libraries, MemberManagement memberManagement) {
		super();
		this.libraryManager = libraryManagement;
		this.libraries = libraries;
		this.memberManager = memberManagement;
	}

	@Override
	public String findMostIssuedBookName() {
		int maxCount = -1;
		Book maxBook = null;
		for (int i = 0; i < libraries.length; i++) {
			for (int j = 0; j < libraries[i].getBooks().length; j++) {
				var currentBook = libraries[i].getBooks()[j];
				var currentCount = 0;
				for (int k = 0; k < libraryManager.getIssues()[i].length; k++) {
					if (libraryManager.getIssues()[i][k].getBook().equals(currentBook)) {
						currentCount++;
					}
				}
				if (currentCount > maxCount) {
					maxBook = currentBook;
					maxCount = currentCount;
				}
			}
		}

		return maxBook.getTitle();
	}

	@Override
	public String findMostIssuedMember() {
		int maxCount = -1;
		Member maxMember = null;

		for (int i = 0; i < memberManager.getMembers().length; i++) {
			var currentMember = memberManager.getMembers()[i];
			var currentCount = 0;

			for (int j = 0; j < libraryManager.getIssues().length; j++) {
				for (int k = 0; k < libraryManager.getIssues()[j].length; k++) {
					if (libraryManager.getIssues()[j][k].getMemberId() == currentMember.getId()) {
						currentCount++;
					}
				}
			}

			if (currentCount > maxCount) {
				maxMember = currentMember;
				maxCount = currentCount;
			}
		}

		return maxMember.getName();
	}

	@Override
	public String findHighestPenaltyForLateReturn() {
		double maxAmount = 0;
		for (var library : libraryManager.getIssues()) {
			for (var issue : library) {
				if (issue.calculateDelayCost() > maxAmount) {
					maxAmount = issue.calculateDelayCost();
				}
			}
		}

		return new String(maxAmount + " TL");
	}

	@Override
	public String findBookWithMostCopies() {
		int maxCopies = 0;
		Book maxCopiedBook = null;
		for (var library : libraries) {
			for (var book : library.getBooks()) {
				if (book.getQuantity() > maxCopies) {
					maxCopiedBook = book;
					maxCopies = book.getQuantity();
				}
			}
		}

		return maxCopiedBook.getTitle();
	}

	@Override
	public String findBookWithLeastCopiesAndIssuedBefore() {
		int leastCopies = Integer.MAX_VALUE;
		Book leastCopiedBook = null;

		for (var library : libraryManager.getIssues())
			for (var book : library) {
				if (book.getBook().getQuantity() < leastCopies) {
					leastCopiedBook = book.getBook();
					leastCopies = book.getBook().getQuantity();
				}
			}

		return leastCopiedBook.getTitle();
	}

	@Override
	public String findLeastIssuedMember(int libraryIndex) {
		int leastCount = Integer.MAX_VALUE;
		Member leastIssuedMember = null;

		for (var member : memberManager.getMembers()) {
			int memberCount = 0;
			for (var issue : libraryManager.getIssues()[libraryIndex]) {
				if (issue.getMember() == member) {
					memberCount++;
				}
			}
			if (memberCount < leastCount) {
				leastIssuedMember = member;
				leastCount = memberCount;
			}
		}
		
		return leastIssuedMember.getName();
	}

}
